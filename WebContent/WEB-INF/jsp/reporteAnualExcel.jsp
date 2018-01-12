<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="pe.gob.dmn.model.ReporteDemunasMesesMaterias"%>
<%@page import="pe.gob.dmn.model.Materia"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<title>Reporte</title>

<style type="text/css">
        .colorlinea{
                                font-family             : Verdana, Arial, Helvetica, sans-serif;                        
                                font-size               : 8pt;
                                background-color:#EBEBEB;                                                                                                                       
                        }       
        .cabecera{                                                              
                                font-size       : 7pt;
                                text-align      :center;
                                color:#000000;
                                background-color:#CCCCCC;
                                height:20px;
                        }
        .meses  {                                                               
                                font-size       : 8pt;
                                text-align      :right;
                                color           :#000000;
                                background-color:#EBEBEB;
                        }
                        
        .tdnumber{                                                              
                                font-size: 8pt;
                                text-align:center;
                                background-color:#FFFFFF;                       
                        }
        .tdnumber1{                                                             
                                font-size: 8pt;
                                text-align:center;
                                background-color:#EBEBEB;                       
                        }
        .vertical{
                        -webkit-transform:rotate(-90deg);
                        -moz-transform:rotate(-90deg);
                        -o-transform: rotate(-90deg);
                        filter:progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
                        }                               
                                        
</style>

</head>
        
        <body>
                
                <%                      
                        int demuna = 1;
                    List<ReporteDemunasMesesMaterias> reporte;
                        String casos1="", casos2="", casos3="", casos4="",casos5="",casos6="", casos7="", casos8="",
                                         casos9="", casos10="", casos11="",      casos12="", casos13="", casos14="", casos15="",
                                         casos16="",casos17="",casos18="",casos19="",casos20="",casos21="",casos22="",
                                         casos23="",casos24="",casos25="",casos26="",casos27="";
                 %>                                                                     
                <table width="1000" border="1" class="colorlinea">                                                                                                                                                                      
                                
                                <tr class="colorlinea">                                         
                                                <td class="cabecera" rowspan="4" >Mes </td>
                                                
                                                <%
                                                List<Materia> materias = (List<Materia>)request.getAttribute("materias");
                                                for (Materia mat : materias) {
                                                %>                              
                                                        <td class="cabecera" width="70"  rowspan="4"><%= mat.getMateria() %></td> 
                                        
                                                <%}%>
                                                
                                                <td class="cabecera" colspan="8" >Afectados</td>        
                                                <td class="cabecera" colspan="6" >Denunciados</td>
                                                <td class="cabecera" rowspan="4"><div class="vertical">Conciliados</div></td>   
                                </tr>   
                                                        
                                <tr class="colorlinea">
                                                <td class="cabecera" colspan="2">0-6 a&ntilde;os </td>
                                                <td class="cabecera" colspan="3">6-12 a&ntilde;os </td>
                                                <td class="cabecera" colspan="3">6-12 a&ntilde;os </td>        
                                                <td class="cabecera" rowspan="3"><div class="vertical">Padre</div></td>
                                                <td class="cabecera" rowspan="3"><div class="vertical">Madre</div></td>
                                                <td class="cabecera" rowspan="3"><div class="vertical">Familiar</div></td>
                                                <td class="cabecera" rowspan="3"><div class="vertical">Profesor</div></td>
                                                <td class="cabecera" rowspan="3"><div class="vertical">Vecino</div></td>
                                                <td class="cabecera" rowspan="3"><div class="vertical">Otro</div></td>
                                                
                                </tr>
                                <tr class="colorlinea">                                         
                                                <td class="cabecera" rowspan="2">H </td>
                                                <td class="cabecera"  rowspan="2">M </td>                                                                       
                                                <td class="cabecera"  rowspan="2">H </td>
                                                <td class="cabecera" colspan="2">M </td>                                                                        
                                                <td class="cabecera" rowspan="2" >H </td>
                                                <td colspan="2" class="cabecera">M </td>                                                        
                                </tr>
                                <tr class="colorlinea">                                  
                                  <td class="cabecera">G</td>
                          <td class="cabecera">NG</td>
                                  <td class="cabecera">G</td>
                                  <td class="cabecera">NG </td>                 
                                </tr>   
                        
                                <%                                                              
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte01");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}
                                                        
                                                }
                                                                                                                                                        
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Enero </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>
                                </tr>
                                
                                
                                <%                            
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte02");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Febrero </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>                                                                                          
                                </tr>
                                
                                
                                <%                                                     
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte03");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                
                                <tr class="colorlinea">
                                                <td class="meses">Marzo </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                                
                                </tr>
                                
                                <%                                                     
                                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                        casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                        casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                        casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte04");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Abril </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%      
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte05");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                
                                <tr class="colorlinea">
                                                <td class="meses">Mayo </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                        
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte06");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Junio </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%  
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte07");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Julio </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%
                                
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte08");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Agosto </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%
                                
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte09");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                                                
                                <tr class="colorlinea">
                                                <td class="meses">Setiembre </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>   
                                
                                
                                <%
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte10");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                                                                                        
                                <tr class="colorlinea">
                                                <td class="meses">Octubre </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%
                                
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte11");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Noviembre </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>  
                                </tr>
                                
                                <%
                                
                                casos1=""; casos2=""; casos3=""; casos4="";casos5="";casos6=""; casos7=""; casos8="";
                                casos9=""; casos10=""; casos11="";      casos12=""; casos13=""; casos14=""; casos15="";
                                casos16="";casos17="";casos18="";casos19="";casos20="";casos21="";casos22="";
                                casos23="";casos24="";casos25="";casos26="";casos27="";
                                
                                                reporte = (List<ReporteDemunasMesesMaterias>) request
                                                                .getAttribute("reporte12");                                                                                                                                                                                                                                                                                                                                                             
                                                
                                                for (ReporteDemunasMesesMaterias rep : reporte) {
                                                        Long casos = rep.getCasos();
                                                        String mater = rep.getMateria();
                                                        String meses = rep.getMeses();
                                                        Integer kymateria = rep.getKymateria();
                                                        
                                                        if (kymateria==1) {casos1 = casos.toString();}
                                                        if (kymateria==2) {casos2 = casos.toString();}
                                                        if (kymateria==3) {casos3 = casos.toString();}
                                                        if (kymateria==4) {casos4 = casos.toString();}
                                                        if (kymateria==5) {casos5 = casos.toString();}
                                                        if (kymateria==6) {casos6 = casos.toString();}
                                                        if (kymateria==7) {casos7 = casos.toString();}
                                                        if (kymateria==8) {casos8 = casos.toString();}
                                                        if (kymateria==9) {casos8 = casos.toString();}
                                                        if (kymateria==10) {casos10 = casos.toString();}
                                                        if (kymateria==11) {casos11 = casos.toString();}
                                                        if (kymateria==12) {casos12 = casos.toString();}
                                                        if (kymateria==13) {casos13 = casos.toString();}
                                                        if (kymateria==14) {casos14 = casos.toString();}
                                                        if (kymateria==15) {casos15 = casos.toString();}
                                                        if (kymateria==16) {casos16 = casos.toString();}
                                                        if (kymateria==17) {casos17 = casos.toString();}
                                                        if (kymateria==18) {casos18 = casos.toString();}
                                                        if (kymateria==19) {casos19 = casos.toString();}
                                                        if (kymateria==20) {casos20 = casos.toString();}
                                                        if (kymateria==21) {casos21 = casos.toString();}
                                                        if (kymateria==22) {casos22 = casos.toString();}
                                                        if (kymateria==23) {casos23 = casos.toString();}
                                                        if (kymateria==24) {casos24 = casos.toString();}
                                                        if (kymateria==25) {casos25 = casos.toString();}
                                                        if (kymateria==26) {casos26 = casos.toString();}
                                                        if (kymateria==27) {casos27 = casos.toString();}                                                        
                                                }                                                                                                                                                       
                                %>
                                
                                <tr class="colorlinea">
                                                <td class="meses">Diciembre </td>
                                                <td class="tdnumber"><%=casos1%></td>
                                                <td class="tdnumber"><%=casos2%></td>
                                                <td class="tdnumber"><%=casos3%></td>
                                                <td class="tdnumber"><%=casos4%></td>
                                                <td class="tdnumber"><%=casos5%></td>
                                                <td class="tdnumber"><%=casos6%></td>
                                                <td class="tdnumber"><%=casos7%></td>
                                                <td class="tdnumber"><%=casos8%></td>
                                                <td class="tdnumber"><%=casos9%></td>
                                                <td class="tdnumber"><%=casos10%></td>
                                                <td class="tdnumber"><%=casos11%></td>
                                                <td class="tdnumber"><%=casos12%></td>
                                                <td class="tdnumber"><%=casos13%></td>
                                                <td class="tdnumber"><%=casos14%></td>
                                                <td class="tdnumber"><%=casos15%></td>
                                                <td class="tdnumber"><%=casos16%></td>
                                                <td class="tdnumber"><%=casos17%></td>
                                                <td class="tdnumber"><%=casos18%></td>
                                                <td class="tdnumber"><%=casos19%></td>
                                                <td class="tdnumber"><%=casos20%></td>
                                                <td class="tdnumber"><%=casos21%></td>
                                                <td class="tdnumber"><%=casos22%></td>
                                                <td class="tdnumber"><%=casos23%></td>
                                                <td class="tdnumber"><%=casos24%></td>
                                                <td class="tdnumber"><%=casos25%></td>
                                                <td class="tdnumber"><%=casos26%></td>
                                                <td class="tdnumber"><%=casos27%></td>          
                                </tr>                                                                                           
                </table>
                
                <div align="right" style="font-size:9pt;">
                                <a href="reporteAnualExcel.htm?dwnldd">Exportar a Excel</a>
                </div>
    
        </body>
        
</html>