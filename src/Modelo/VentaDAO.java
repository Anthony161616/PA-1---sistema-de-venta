
package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class VentaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    
    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO ventas (rucdni,cliente, vendedor, total, fecha) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getRucdnicliente());
            ps.setString(2, v.getNombre_cli());
            ps.setString(3, v.getVendedor());
            ps.setDouble(4, v.getTotal());
            ps.setString(5, v.getFecha());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public int RegistrarDetalle(Detalle Dv){
       String sql = "INSERT INTO detalle_venta (codigo_producto, cantidad, precio, id_venta) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Dv.getId_pro());
            ps.setInt(2, Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public boolean ActualizarStock(int cant, int id){
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cant);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List Listarventas(){
       List<VentaReporte> ListaVenta = new ArrayList();
       String sql = "SELECT c.id AS id_cli, c.nombre, v.vendedor,v.total FROM clientes c INNER JOIN ventas v ON c.dni = v.rucdni";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {   
               VentaReporte vent = new VentaReporte();
               vent.setId(rs.getInt("id_cli"));
               vent.setNombre(rs.getString("nombre"));
               vent.setVendedor(rs.getString("vendedor"));
               vent.setTotal(rs.getDouble("total"));
               ListaVenta.add(vent);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaVenta;
   }
    
    public Venta BuscarVenta(int id){
        Venta cl = new Venta();
        String sql = "SELECT * FROM ventas WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setRucdnicliente(rs.getString("rucdni"));
                cl.setNombre_cli(rs.getString("cliente"));
                cl.setTotal(rs.getDouble("total"));
                cl.setVendedor(rs.getString("vendedor"));
                cl.setFecha(rs.getString("fecha"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }
    
    public void pdfV() {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "venta.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/Img/logo01.png"));

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("\nFecha: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");
            //info empresa
            String config = "SELECT * FROM config";
            String mensaje = "";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(config);
                rs = ps.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    Encabezado.addCell("Ruc:    " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n");
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
   
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
   
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("REPORTE DE VENTAS" + "\n\n");
            doc.add(cli);

            PdfPTable proveedor = new PdfPTable(3);
            proveedor.setWidthPercentage(100);
            proveedor.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{50f, 25f, 25f};
            proveedor.setWidths(columnWidthsCliente);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cliTel = new PdfPCell(new Phrase("Télefono", negrita));
            PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            cliTel.setBorder(Rectangle.NO_BORDER);
            cliDir.setBorder(Rectangle.NO_BORDER);
            proveedor.addCell(cliNom);
            proveedor.addCell(cliTel);
            proveedor.addCell(cliDir);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("ID.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("CLIENTE.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("VENDEDOR.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("TOTAL", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            double total=0;
            String consulta = "SELECT c.id AS id_cli, c.nombre, v.vendedor,v.total FROM clientes c INNER JOIN ventas v ON c.dni = v.rucdni";
            try {
                ps = con.prepareStatement(consulta);
                rs = ps.executeQuery();
                while (rs.next()) {
                
                    tabla.addCell(rs.getString("id_cli"));
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(rs.getString("vendedor"));
                    tabla.addCell(rs.getString("total"));
                    total=total+Double.parseDouble(rs.getString("total"));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(tabla);
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
//            Paragraph firma = new Paragraph();
//            firma.add(Chunk.NEWLINE);
//            firma.add("Cancelacion \n\n");
//            firma.add("------------------------------------\n");
//            firma.add("Firma \n");
//            firma.setAlignment(Element.ALIGN_CENTER);
//            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
    
     public void pdfTicket(String nomvendedor,String dni,String nomcliente,JTable tabladatos ) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "tiket.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            Image img = Image.getInstance(getClass().getResource("/Img/logo01.png"));
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            Paragraph fecha = new Paragraph("",negrita);
            
            fecha.add(Chunk.NEWLINE);
            fecha.add("\nFecha: " + new SimpleDateFormat("dd/MM/yyyy").format(date)+"\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");
            Encabezado.addCell("");
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            
            //info empresa
            String config = "SELECT * FROM config";
            String mensaje = "";
            String empresa="";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(config);
                rs = ps.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    empresa="Ruc:    " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n";
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
              
            Font negrita1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLUE);
            Paragraph nempresa = new Paragraph("",negrita1);
            PdfPTable Encabezadoemp = new PdfPTable(4);
            Encabezadoemp.setWidthPercentage(100);
            Encabezadoemp.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezadoemp = new float[]{20f, 30f, 70f, 40f};
            Encabezadoemp.setWidths(columnWidthsEncabezadoemp);
            Encabezadoemp.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezadoemp.addCell(empresa);
            Encabezadoemp.addCell("");
            Encabezadoemp.addCell("");
            Encabezadoemp.addCell("");
            //nempresa.add(Chunk.NEWLINE);
            nempresa.add(empresa);
            //nempresa.add(Chunk.NEWLINE);
            doc.add(nempresa);
            
            Font negrita2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            Paragraph vendedor = new Paragraph("",negrita2);
            PdfPTable Encabezadoven = new PdfPTable(3);
            Encabezadoven.setWidthPercentage(100);
            Encabezadoven.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezadoven = new float[]{25f, 25f, 25f};
            Encabezadoven.setWidths(columnWidthsEncabezadoven);
            Encabezadoven.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezadoven.addCell(empresa);
            Encabezadoven.addCell("");
            Encabezadoven.addCell("");
            vendedor.add(Chunk.NEWLINE);
            vendedor.add("Vendedor:"+nomvendedor);
            vendedor.add(Chunk.NEWLINE);
            doc.add(vendedor);
            
            Paragraph cliente = new Paragraph("",negrita2);
            PdfPTable Encabezadocli = new PdfPTable(3);
            Encabezadocli.setWidthPercentage(100);
            Encabezadocli.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezadocli = new float[]{25f, 25f, 25f};
            Encabezadocli.setWidths(columnWidthsEncabezadocli);
            Encabezadocli.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezadocli.addCell("");
            Encabezadocli.addCell("");
            Encabezadocli.addCell("");
            cliente.add("Cliente Dni/Ruc:"+dni+"                       ");
            cliente.add("Cliente Nombres:"+nomcliente);
            cliente.add("\n\n");
            doc.add(cliente);
            
            Font negrita3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
            Paragraph titulo = new Paragraph("",negrita3);
            PdfPTable encabezadotitu = new PdfPTable(3);
            encabezadotitu.setWidthPercentage(100);
            encabezadotitu.getDefaultCell().setBorder(0);
            float[] columnWidthsTitulo = new float[]{25f, 25f, 25f};
            encabezadotitu.setWidths(columnWidthsTitulo);
            encabezadotitu.setHorizontalAlignment(Element.ALIGN_LEFT);
            encabezadotitu.addCell("");
            encabezadotitu.addCell("");
            encabezadotitu.addCell("");
            titulo.add("");
            titulo.add("                                                                Ticket de venta");
            titulo.add("\n\n");
            doc.add(titulo);
            
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{20f, 50f, 18f, 18f,18f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("CODIGO", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("DESCRIPCION.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("CANTIDAD.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("PRECIO", negrita));
            PdfPCell c5= new PdfPCell(new Phrase("TOTAL", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c5.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            tabla.addCell(c5);
            double total=0;
         
            try {
                 for(int z=0;z<tabladatos.getColumnCount();z++){
                    tabla.addCell(tabladatos.getValueAt(z, 0).toString());
                    tabla.addCell(tabladatos.getValueAt(z, 1).toString());
                    tabla.addCell(tabladatos.getValueAt(z, 2).toString());
                    tabla.addCell(tabladatos.getValueAt(z, 3).toString());
                    tabla.addCell(tabladatos.getValueAt(z, 4).toString());
                    total=total+Double.parseDouble(tabladatos.getValueAt(z, 4).toString());
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            doc.add(tabla);
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
//            Paragraph firma = new Paragraph();
//            firma.add(Chunk.NEWLINE);
//            firma.add("Cancelacion \n\n");
//            firma.add("------------------------------------\n");
//            firma.add("Firma \n");
//            firma.setAlignment(Element.ALIGN_CENTER);
//            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
 
}
