package com.tartyp.otchet.services;

import com.tartyp.otchet.DTO.DepDTOPositions;
import com.tartyp.otchet.DTO.DepartmentsDTO;
import com.tartyp.otchet.DTO.UserpositionsDTO;
import com.tartyp.otchet.entity.*;
import com.tartyp.otchet.repo.*;
import feign.Response;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {
    // bad ones 12;18;58;10;16
    private short[] color = {IndexedColors.AQUA.getIndex(), IndexedColors.LIGHT_YELLOW.getIndex(),
            IndexedColors.LIGHT_GREEN.getIndex(),
            (short) 47, (short) 49, (short) 66};

    private Boolean[] options = {false, true};

    private String[] departmentLvlNames = {"Первый уроверь", "Второй уровень", "Третий уровень", "Четвертый уровень",
            "Пятый уровень", "Шестой уровень", "Седьмой уроверь", "Восьмой уровень",
            "Девятый уровень", "Десятый уровень"};


    @Autowired
    com.tartyp.otchet.repo.DepartmentsRepo DepartmentsRepo;

    @Autowired
    com.tartyp.otchet.repo.PositionsRepo PositionsRepo;

    @Autowired
    com.tartyp.otchet.repo.TranslationRepo TranslationRepo;

    @Autowired
    UserpositionsRepo userpositionsRepo;

    @Autowired
    ActionsService ActionsService;

    @Autowired
    com.tartyp.otchet.repo.UsersRepo UsersRepo;

    @Autowired
    FeignClientRepo feignClientRepo;

    @Autowired
    WorkingDaysService workingDaysService;

    public XSSFWorkbook getXlSXList(Date start, Date finish, Boolean showLevelNames, String departmentId, Boolean showEmptyDepartments, Boolean showPositionLevels) throws IOException, URISyntaxException {
        ClassPathResource classPathResource = new ClassPathResource("tartyp.xlsx");
        InputStream input = classPathResource.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheetAt(0);
        EachRowIndex rowIndex = new EachRowIndex(3, 1, false, showLevelNames, showEmptyDepartments, showPositionLevels);
        sheet = setDepartmentsData(getSynergyColor(), getSynergyHolidays(), departmentId, start, finish, sheet, workbook, rowIndex, true);
        return workbook;
    }

    public String[] getSynergyColor() throws URISyntaxException {
        String[] colors = {"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"};
        Response response = feignClientRepo.getDictionary("AUTH", new URI("https://arta.tartyp.kz/Synergy/rest/api/dictionaries/otchet_po_ispolnitel_skoi_distsipline?getColumns=false"));
        try {
            JSONObject dictionaryData = null;
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(response.body().asInputStream()))) {
                String resp = buffer.lines().collect(Collectors.joining("\n"));
                dictionaryData = new JSONObject(resp);
                Iterator keys = dictionaryData.getJSONObject("items").keys();
                while (keys.hasNext()) {
                    try {
                        JSONObject jsonDictColors = dictionaryData.getJSONObject("items").getJSONObject((String) keys.next());
                        int index = (int) jsonDictColors.getJSONObject("departments_lvl").getInt("value");
                        colors[index - 1] = ((String) jsonDictColors.getJSONObject("departments_color").get("value")).replaceAll(" ", "");
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
                return colors;
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to process response body.", ex);
        }
    }

    public Set<LocalDate> getSynergyHolidays() throws URISyntaxException {
        List<LocalDate> dates = new ArrayList<>();
        Response response = feignClientRepo.getDictionary("AUTH", new URI("https://arta.tartyp.kz/Synergy/rest/api/dictionaries/otchet_po_ispolnitel_skoi_distsipline_prazdniki?getColumns=false"));
        try {
            JSONObject dictionaryData = null;
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(response.body().asInputStream()))) {
                String resp = buffer.lines().collect(Collectors.joining("\n"));
                dictionaryData = new JSONObject(resp);
                Iterator keys = dictionaryData.getJSONObject("items").keys();
                while (keys.hasNext()) {
                    JSONObject holidaysDict = dictionaryData.getJSONObject("items").getJSONObject((String) keys.next());
                    try {
                        dates.add(LocalDate.of(holidaysDict.getJSONObject("holiday_year").getInt("value"), holidaysDict.getJSONObject("holiday_month").getInt("value"), holidaysDict.getJSONObject("holiday_day").getInt("value")));
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }

                return Collections.unmodifiableSet(new HashSet<>(dates));
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to process response body.", ex);
        }
    }

    public XSSFCellStyle getXSSFStyle(String col, Boolean optional, XSSFWorkbook workbookMain, Boolean departmentLvlNames) {
        XSSFCellStyle xSSFCellStyle = workbookMain.createCellStyle();
        XSSFFont font = workbookMain.createFont();
        if (departmentLvlNames) {
            font.setColor(IndexedColors.RED.getIndex());
        } else {
            xSSFCellStyle.setBorderBottom(BorderStyle.DASHED);
            xSSFCellStyle.setBorderLeft(BorderStyle.DASHED);
            xSSFCellStyle.setBorderRight(BorderStyle.DASHED);
            xSSFCellStyle.setBorderTop(BorderStyle.DASHED);
            xSSFCellStyle.setAlignment(HorizontalAlignment.CENTER);
            xSSFCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            try {
                xSSFCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode(col)));
            } catch (Exception err) {
                xSSFCellStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#FFFFFF")));
            }
            xSSFCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Times New Roman");
        //System.out.println(col);
        xSSFCellStyle.setWrapText(true);
        if (optional) {
            font.setBold(false);
            xSSFCellStyle.setAlignment(HorizontalAlignment.LEFT);
        }
        xSSFCellStyle.setFont(font);
        return xSSFCellStyle;
    }


    public XSSFSheet setLabels(XSSFSheet sheet, Date start, Date finish, XSSFCellStyle xSSFCellStyle, EachRowIndex rowIndex) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (start != finish) {
            sheet.getRow(1).getCell(1).setCellValue("за время c " + dateFormat.format(start) + " по " + dateFormat.format(finish));
        }
        String[] ServiceDataIds = {"№", "Исполнитель", "Всего работ", "Выполнено", "В работе", "Дней просрочки за 3 года", "В срок", "С опозданием", " За период", " За 3 года", "max", "min"};

        try {
            for (int i = 1; i < 4; i++) {
                try {
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex.getRow(), rowIndex.getRow() + 1, i, i));
                } catch (Exception err) {
                    //System.out.print("err1 -");
                }
                sheet.getRow(rowIndex.getRow()).getCell(i).setCellValue(ServiceDataIds[i - 1]);
                sheet.getRow(rowIndex.getRow()).getCell(i).setCellStyle(xSSFCellStyle);
                sheet.getRow(rowIndex.getRow() + 1).getCell(i).setCellStyle(xSSFCellStyle);
            }
            try {
                if (rowIndex.getShowPositionLevels()) {
                    sheet.getRow(rowIndex.getRow()).createCell(10);
                    sheet.getRow(rowIndex.getRow() + 1).createCell(10);
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex.getRow(), rowIndex.getRow() + 1, 10, 10));
                }
            } catch (Exception err) {

            }
        } catch (Exception err) {
            System.out.println("labels error 1");
            System.out.println(err);
        }

        try {
            for (int i = 4; i < 9; i = i + 2) {
                sheet.getRow(rowIndex.getRow()).createCell(i);
                sheet.getRow(rowIndex.getRow()).createCell(i + 1);
                try {
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex.getRow(), rowIndex.getRow(), i, i + 1));
                } catch (Exception err) {
                    //System.out.print("err2 -");
                }
                sheet.getRow(rowIndex.getRow()).getCell(i).setCellValue(ServiceDataIds[(int) i / 2 + 1]);

                sheet.getRow(rowIndex.getRow()).getCell(i).setCellStyle(xSSFCellStyle);
                sheet.getRow(rowIndex.getRow()).getCell(i + 1).setCellStyle(xSSFCellStyle);

                sheet.getRow(rowIndex.getRow() + 1).createCell(i);
                sheet.getRow(rowIndex.getRow() + 1).createCell(i + 1);

                sheet.getRow(rowIndex.getRow() + 1).getCell(i).setCellValue(ServiceDataIds[(int) i + 2]);
                sheet.getRow(rowIndex.getRow() + 1).getCell(i + 1).setCellValue(ServiceDataIds[(int) i + 3]);

                sheet.getRow(rowIndex.getRow() + 1).getCell(i).setCellStyle(xSSFCellStyle);
                sheet.getRow(rowIndex.getRow() + 1).getCell(i + 1).setCellStyle(xSSFCellStyle);
            }
        } catch (Exception err) {
            System.out.println("labels error 2");
            System.out.println(err);
        }

        try {
            if (rowIndex.getShowPositionLevels()) {
                sheet.getRow(rowIndex.getRow()).getCell(10).setCellValue("№ номер должности");
                sheet.getRow(rowIndex.getRow()).getCell(10).setCellStyle(xSSFCellStyle);
                sheet.getRow(rowIndex.getRow() + 1).getCell(10).setCellStyle(xSSFCellStyle);
            }
        } catch (Exception err) {
            System.out.println("position number error");
            System.out.println(err);
        }
        rowIndex.setRow(rowIndex.getRow() + 2);
        rowIndex.setPass(true);
        //System.out.println(rowIndex);
        return sheet;
    }

    public XSSFSheet setDepartmentsData(String[] colors, Set<LocalDate> holidays, String departmentId, Date start, Date finish, XSSFSheet sheet, XSSFWorkbook workbookMain, EachRowIndex rowIndex, Boolean parent) {
        //быстро
        List<DepartmentsEntity> dep = null;
        if (parent) {
            dep = DepartmentsRepo.findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThanAndDepartmentId(0, 200, departmentId);
        } else {
            dep = DepartmentsRepo.findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThanAndParentDepartmentId(0, 200, departmentId);
        }
        Collections.sort(dep);
        for (DepartmentsEntity department : dep) {
            if (department.getLevel() > 0) {
                try {
                    String col = colors[(department.getLevel() - 1)];
                    // быстро
                    sheet = setLabels(sheet, start, finish, getXSSFStyle(col, false, workbookMain, false), rowIndex);
                    sheet.getRow(rowIndex.getRow() - 2).getCell(2).setCellValue(TranslationRepo.findById(new TranslationsEntityPK(department.getTranslationId(),
                            "ru")).get().getText());

                    if (rowIndex.showLevelNames) {
                        //System.out.println(department.getLevel());

                        try {
                            sheet.getRow(rowIndex.getRow() - 1).createCell(11);
                            sheet.getRow(rowIndex.getRow() - 1).createCell(12);
                            sheet.addMergedRegion(new CellRangeAddress(rowIndex.getRow() - 1, rowIndex.getRow() - 1, 11, 12));
                        } catch (Exception err) {
                            //System.out.print("err2 -");
                        }
                        sheet.getRow(rowIndex.getRow() - 1).getCell(11).setCellValue(departmentLvlNames[(department.getLevel() - 1)]);
                        sheet.getRow(rowIndex.getRow() - 1).getCell(11).setCellStyle(getXSSFStyle(col, false, workbookMain, true));
                    }

                    //System.out.println(rowIndex);
                    sheet = setPositionsData(holidays, start, finish, sheet, department.getDepartmentId(), getXSSFStyle(col, true, workbookMain, false), rowIndex);
                    sheet = setDepartmentsData(colors, holidays, department.getDepartmentId(), start, finish, sheet, workbookMain, rowIndex, false);
                } catch (Exception err) {
                    System.out.println("departments error");
                    System.out.println(err);
                }
            }
        }
        return sheet;
    }

    public XSSFSheet setPositionsData(Set<LocalDate> holidays, Date start, Date finish, XSSFSheet sheet, String depId, XSSFCellStyle xSSFCellStyle, EachRowIndex rowIndex) {
        try {
            //быстро
            List<PositionsEntity> positions = PositionsRepo.findByDepartmentIdAndDeletedIsNullAndPointerCodeIsNotNull(depId);
            Collections.sort(positions);
            for (PositionsEntity position : positions) {
                //TranslationRepo.findById(new TranslationsEntityPK(position.getTranslationId(), "ru")).get().getText(); // position name
                //быстро
                userpositionsRepo.findByPositionIdAndFinishdateIsNull(position.getPositionId()).forEach(user -> {
                    List<ActionsEntity> notFinishedForYear = ActionsService.getNotFinishedForYear(user.getUserId(), finish);
                    // медленно
                    List<ActionsEntity> tot = ActionsService.getAll(user.getUserId(), start, finish, 1);
                    int totalFinished = tot.size();
                    //System.out.println(totalFinished + notFinishedForYear.size());
                    if ((totalFinished + notFinishedForYear.size() > 0)) {
                        // быстро
                        int finishedOnTime = 0;
                        for (ActionsEntity a : tot) {
                            if (a.getFinished().compareTo(a.getFinishDate()) <= 0) {
                                finishedOnTime++;
                            }
                        }
                        int row = rowIndex.getRow();
                        sheet.getRow(row).createCell(4);
                        sheet.getRow(row).getCell(4).setCellValue(finishedOnTime);
                        sheet.getRow(row).getCell(4).setCellStyle(xSSFCellStyle);

                        sheet.getRow(row).createCell(5);
                        sheet.getRow(row).getCell(5).setCellValue(totalFinished - finishedOnTime);
                        sheet.getRow(row).getCell(5).setCellStyle(xSSFCellStyle);

                        sheet.getRow(row).createCell(6);
                        sheet.getRow(row).getCell(6).setCellValue(ActionsService.getNotFinished(user.getUserId(), start, finish).size());
                        sheet.getRow(row).getCell(6).setCellStyle(xSSFCellStyle);

                        sheet.getRow(row).createCell(7);
                        sheet.getRow(row).getCell(7).setCellValue(notFinishedForYear.size());
                        sheet.getRow(row).getCell(7).setCellStyle(xSSFCellStyle);

                        Collections.sort(notFinishedForYear);
                        if (notFinishedForYear.size() > 0) {
                            int days = workingDaysService.getBusinessDays(holidays, notFinishedForYear.get(0).getFinishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
                            sheet.getRow(row).createCell(8);
                            sheet.getRow(row).getCell(8).setCellValue(days);
                            sheet.getRow(row).getCell(8).setCellStyle(xSSFCellStyle);

                            days = workingDaysService.getBusinessDays(holidays, notFinishedForYear.get(notFinishedForYear.size() - 1).getFinishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
                            if (days == 0) {
                                days = 1;
                            }
                            sheet.getRow(row).createCell(9);
                            sheet.getRow(row).getCell(9).setCellValue(days);
                            sheet.getRow(row).getCell(9).setCellStyle(xSSFCellStyle);
                        } else {
                            sheet.getRow(row).createCell(8);
                            sheet.getRow(row).getCell(8).setCellValue(0);
                            sheet.getRow(row).getCell(8).setCellStyle(xSSFCellStyle);

                            sheet.getRow(row).createCell(9);
                            sheet.getRow(row).getCell(9).setCellValue(0);
                            sheet.getRow(row).getCell(9).setCellStyle(xSSFCellStyle);
                        }

                        if (rowIndex.getShowPositionLevels()) {
                            sheet.getRow(row).createCell(10);
                            sheet.getRow(row).getCell(10).setCellValue(position.getNumber());
                            sheet.getRow(row).getCell(10).setCellStyle(xSSFCellStyle);
                        }

                        sheet.getRow(row).createCell(1);
                        sheet.getRow(row).getCell(1).setCellValue(rowIndex.getIndex());
                        sheet.getRow(row).getCell(1).setCellStyle(xSSFCellStyle);

                        UsersEntity userData = UsersRepo.findById(user.getUserId()).get();
                        String userName = userData.getLastname() + " ";
                        try {
                            userName = userName + userData.getFirstname().charAt(0) + ".";
                            userName = userName + userData.getPatronymic().charAt(0) + ".";
                        } catch (Exception err) {

                        }
                        sheet.getRow(row).createCell(2);
                        sheet.getRow(row).getCell(2).setCellValue(userName + " - " + TranslationRepo.findById(new TranslationsEntityPK(position.getTranslationId(), "ru")).get().getText());
                        sheet.getRow(row).getCell(2).setCellStyle(xSSFCellStyle);

                        sheet.getRow(row).createCell(3);
                        sheet.getRow(row).getCell(3).setCellValue(totalFinished + notFinishedForYear.size());
                        sheet.getRow(row).getCell(3).setCellStyle(xSSFCellStyle);

                        rowIndex.setRow(rowIndex.getRow() + 1); // переход на следующую строку\
                        //System.out.println(rowIndex.getIndex());
                        rowIndex.setIndex(rowIndex.getIndex() + 1);
                        rowIndex.setPass(false);
                    }
                });
            }
        } catch (Exception err) {
            //System.out.println(row + "--");
            System.out.println(depId);
            System.out.println(err);
        }

        if (rowIndex.getPass() && !(rowIndex.getShowEmptyDepartments())) {
            rowIndex.setRow(rowIndex.getRow() - 2);
            rowIndex.setPass(false);
        } else {
            rowIndex.setRow(rowIndex.getRow() + 1); // лишняя строка
        }
        return sheet;
    }


    public List<DepartmentsDTO> getAll(Date start, Date finish) {
        List<DepartmentsDTO> depDTOList = new ArrayList<>();
        DepartmentsRepo.findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThan(0, 200).forEach(element -> {
            try {
                if (element.getLevel() > 0) {
                    DepartmentsDTO depDTO = new DepartmentsDTO(element.getDepartmentId(), element.getParentDepartmentId(), TranslationRepo.findById(new TranslationsEntityPK(element.getTranslationId(),
                            "ru")).get().getText(), element.getStructNumber(), element.getLevel(), element.getTranslationId(), element.getTypeId(), element.getNumber());

                    List<PositionsEntity> a = PositionsRepo.findByDepartmentIdAndDeletedIsNullAndPointerCodeIsNotNull(element.getDepartmentId());
                    depDTO.setPositions(ListEntityToDto(a, start, finish));
                    //depDTO.setFullPosition(PositionsRepo.findByDepartmentIdAndDeletedIsNullAndPointerCodeIsNotNull(element.getDepartmentId()));
                    depDTOList.add(depDTO);

                }
            } catch (Exception err) {
                System.out.println(err);
            }
        });
        Collections.sort(depDTOList);
        return depDTOList;
    }


    public List<DepartmentsEntity> getAll(Boolean pass) {
        //List<DepartmentsEntity> dep = DepartmentsRepo.findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThan(0, 200);
        //Collections.sort(dep);
        return new ArrayList<DepartmentsEntity>();
    }

    public DepDTOPositions EntityToDto(PositionsEntity ent, Date start, Date finish) {
        DepDTOPositions dto = new DepDTOPositions();
        dto.setName(TranslationRepo.findById(new TranslationsEntityPK(ent.getTranslationId(), "ru")).get().getText());
        dto.setPositionId(ent.getPositionId());
        List<UserpositionsDTO> AllUsersData = new ArrayList<>();
        userpositionsRepo.findByPositionIdAndFinishdateIsNull(ent.getPositionId()).forEach(element -> {
            AllUsersData.add(EntityToDto(element, start, finish));
        });
        dto.setFullUsers(AllUsersData);
        return dto;
    }

    public List<DepDTOPositions> ListEntityToDto(List<PositionsEntity> ents, Date start, Date finish) {
        List<DepDTOPositions> dtos = new ArrayList<>();
        ents.forEach(ent -> dtos.add(EntityToDto(ent, start, finish)));
        return dtos;
    }


    public UserpositionsDTO EntityToDto(UserpositionsEntity ent, Date start, Date finish) {
        UserpositionsDTO dto = new UserpositionsDTO();
        dto.setStartdate(ent.getStartdate());
        dto.setUserId(ent.getUserId());

        dto.setTotalFinished(ActionsService.getAll(ent.getUserId(), start, finish).intValue());
        dto.setFinishedOnTime(ActionsService.getFinishedOnTime(ent.getUserId(), start, finish).intValue());
        dto.setFinishedLate(dto.getTotalFinished() - dto.getFinishedOnTime());
        dto.setNotFinished(ActionsService.getNotFinished(ent.getUserId(), start, finish).size());

        List<ActionsEntity> notFinishedForYear = ActionsService.getNotFinishedForYear(ent.getUserId(), finish);
        dto.setNotFinishedInYear(notFinishedForYear.size());
        Collections.sort(notFinishedForYear);
        if (notFinishedForYear.size() > 0) {
            dto.setMaxDelay(ChronoUnit.DAYS.between(new Date(notFinishedForYear.get(0).getFinishDate().getTime()).toInstant(), new Date().toInstant()));
            dto.setMinDelay(ChronoUnit.DAYS.between(new Date(notFinishedForYear.get(notFinishedForYear.size() - 1).getFinishDate().getTime()).toInstant(), new Date().toInstant()));
        } else {
            dto.setMaxDelay((long) 0);
            dto.setMinDelay((long) 0);
        }
        dto.setName(UsersRepo.findById(ent.getUserId()).get().getLastname());

        dto.setTotalActions(dto.getTotalFinished() + dto.getNotFinishedInYear());
        return dto;
    }


    @Data
    public class DepDTOUsers {
        private String name;
        private String position;
    }

    @Data
    public class EachRowIndex {
        private int row;

        public EachRowIndex(Integer row, Integer index, Boolean pass, Boolean showLevelNames, Boolean showEmptyDepartments, Boolean showPositionLevels) {
            this.row = row;
            this.index = index;
            this.pass = pass;
            this.showLevelNames = showLevelNames;
            this.showEmptyDepartments = showEmptyDepartments;
            this.showPositionLevels = showPositionLevels;
        }

        private int index;
        private Boolean pass;
        private Boolean showLevelNames;
        private Boolean showEmptyDepartments;
        private Boolean showPositionLevels;
    }
}
