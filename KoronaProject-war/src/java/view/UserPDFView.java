/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**C:/Users/Julia/Documents/AdvancedWebComponents/
 *
 * @author Julia
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entity.Room;
import entity.Roombooking;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserPDFView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
            PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws IOException, FileNotFoundException, DocumentException, NamingException {

        if (model.get("dateReport") != null) {
Date date = new Date();
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:/KoronaHotel/WebReports/DateReports/SwingRoomDateReport"+date.getTime()+".pdf"));

            List<Roombooking> roomBookingsList = (List) model.get("dateReport");

            PdfPTable table = new PdfPTable(7);
            table.addCell("Room name");
            table.addCell("Room type");
            table.addCell("Room floor");
            table.addCell("Room price");
            table.addCell("Room capacity");
            table.addCell("Check in");
            table.addCell("Check out");

            for (Roombooking roomBookings : roomBookingsList) {
                table.addCell(String.valueOf(roomBookings.getRoomId().getName()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getRoomType().getRoomTypeName()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getFloor()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getRoomPrice()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getCapacity()));
                table.addCell(String.valueOf(roomBookings.getCheckIn()));
                table.addCell(String.valueOf(roomBookings.getCheckOut()));
            }

            document.open();
            document.add(new Paragraph("Generated report of booked rooms"));
            document.add(new Paragraph(" "));
            document.add(table);
            document.close();

        } else if (model.get("floorReport") != null) {
Date date = new Date();
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:/KoronaHotel/WebReports/FloorReports/SwingRoomFloorReport"+date.getTime()+".pdf"));

            List<Room> roomsList = (List) model.get("floorReport");

            PdfPTable table = new PdfPTable(5);
            table.addCell("Room name");
            table.addCell("Room type");
            table.addCell("Room floor");
            table.addCell("Room price");
            table.addCell("Room capacity");

            for (Room rooms : roomsList) {
                table.addCell(String.valueOf(rooms.getName()));
                table.addCell(String.valueOf(rooms.getRoomType().getRoomTypeName()));
                table.addCell(String.valueOf(rooms.getFloor()));
                table.addCell(String.valueOf(rooms.getRoomPrice()));
                table.addCell(String.valueOf(rooms.getCapacity()));
            }

            document.open();
            document.add(new Paragraph("Generated report of rooms by floor"));
            document.add(new Paragraph(" "));
            document.add(table);
            document.close();

        } else if (model.get("unpaidRoomsReport") != null) {
Date date = new Date();
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:/KoronaHotel/WebReports/TransactionReports/SwingUnpaidRoomReport"+date.getTime()+".pdf"));

            List<Roombooking> roomBookingsList = (List) model.get("unpaidRoomsReport");

            PdfPTable table = new PdfPTable(7);
            table.addCell("Room name");
            table.addCell("Room type");
            table.addCell("Room floor");
            table.addCell("Room price");
            table.addCell("Room capacity");
            table.addCell("Check in");
            table.addCell("Check out");

            for (Roombooking roomBookings : roomBookingsList) {
                table.addCell(String.valueOf(roomBookings.getRoomId().getName()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getRoomType().getRoomTypeName()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getFloor()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getRoomPrice()));
                table.addCell(String.valueOf(roomBookings.getRoomId().getCapacity()));
                table.addCell(String.valueOf(roomBookings.getCheckIn()));
                table.addCell(String.valueOf(roomBookings.getCheckOut()));
            }

            document.open();
            document.add(new Paragraph("Generated report of rooms with uncompleted transactions"));
            document.add(new Paragraph(" "));
            document.add(table);
            document.close();

        } else if (model.get("roomsByRoomType") != null) {
Date date = new Date();
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:/KoronaHotel/WebReports/RoomTypeReports/SwingRoomTypeReport"+date.getTime()+".pdf"));

            List<Room> roomsList = (List) model.get("roomsByRoomType");

            PdfPTable table = new PdfPTable(5);

            table.setHeaderRows(1);
            table.setSplitRows(false);
            table.setComplete(false);

            table.addCell("Room name");
            table.addCell("Room type");
            table.addCell("Room floor");
            table.addCell("Room price");
            table.addCell("Room capacity");

            for (Room rooms : roomsList) {
                table.addCell(String.valueOf(rooms.getName()));
                table.addCell(String.valueOf(rooms.getRoomType().getRoomTypeName()));
                table.addCell(String.valueOf(rooms.getFloor()));
                table.addCell(String.valueOf(rooms.getRoomPrice()));
                table.addCell(String.valueOf(rooms.getCapacity()));
            }

            document.open();
            document.add(new Paragraph("Generated report of rooms by room type"));
            document.add(new Paragraph(" "));
            document.add(table);
            document.close();

        }

    }

}