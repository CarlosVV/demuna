<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="usrLogin" scope="session" class="pe.gob.dmn.model.Usuario"/>
<jsp:useBean id="demunaLogin" scope="session" class="pe.gob.dmn.model.Demuna"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Registro de Expedientes - Demuna 2013</title>

	<link rel="stylesheet" type="text/css" href="resources/ext-all.css">
	<link rel="stylesheet" type="text/css" href="css/grid-examples.css">
	<link rel="stylesheet" type="text/css" href="css/principal.css">
	
	
	<script type="text/javascript" src="extjs/ext-base.js"></script>
    <script type="text/javascript" src="extjs/ext-all.js"></script>	
	<script type="text/javascript" src="js/FileUploadField.js"></script>
	<script type="text/javascript" src="js/RowEditor.js"></script>
	
	<script type="text/javascript" src="js/frmloginupdate.js"></script>
	<script type="text/javascript" src="js/niveldemunas.js"></script>
	
	<script type="text/javascript" src="js/principal.js"></script>
	<script type="text/javascript" >
	
	var txtNombres = new Ext.form.TextField({
				fieldLabel:'Nombres',
				name: 'txtNombres',								
				width: 350,				
				value:'<%=usrLogin.getNombres()%>'									
		});
	
	var txtAppaterno = new Ext.form.TextField({
		fieldLabel:'Ap.Paterno',
		name: 'txtAppaterno',								
		width: 350,				
		value:'<%=usrLogin.getAppaterno()%>'									
		});
	
	var txtApmaterno = new Ext.form.TextField({
		fieldLabel:'Ap.Materno',
		name: 'txtApmaterno',								
		width: 350,				
		value:'<%=usrLogin.getApmaterno()%>'									
		});
		
	var txtCorreo = new Ext.form.TextField({
				fieldLabel:'Email',
				name: 'txtCorreo',								
				width: 350,						
				value:'<%=usrLogin.getEmail()%>'										
		});
		
	var txtTelefonos = new Ext.form.TextArea({
				fieldLabel:'Telefonos',
				name: 'txtTelefonos',								
				width: 350,	
				height: 80,				
				value:'<%=usrLogin.getTelefonos()%>'										
		});
				
	var txtKyUsuario = new Ext.form.TextField({
				fieldLabel:'KyUsuario',
				name: 'txtKyUsuario',
				readOnly:true,													
				width: 80,
				value:'<%=usrLogin.getKyusuario()%>'
		});
	
	var txtKyNivelUsuario = new Ext.form.TextField({
				fieldLabel:'KyUsuario',
				name: 'txtKyUsuario',
				readOnly:true,													
				width: 80,
				value:'<%=usrLogin.getNivel()%>'
		});
			
	var txtKyDemunaIni = new Ext.form.Hidden({			
				name: 'txtKyDemunaIni',	
				fieldLabel:'Demuna',													
				width: 80,
				value:'<%=usrLogin.getDemuna().getKydemuna()%>'
		});
	</script>	
	

	
</head>

	<body style="color:#006600">
			<%
				String pgn = request.getParameter("pagin")!=null ? request.getParameter("pagin")+"":"";
				String KyDemunaIni = usrLogin.getDemuna().getKydemuna().toString();
			%>
			<div id="container" <% if (demunaLogin.getImagen()!=null) { %>style="background-image:url(${pageContext.request.contextPath}/descargarimagen.htm?id=<%=demunaLogin.getKydemuna()%>)"<% } %>>					
					<div align="left" style="position:absolute; top:15px; font-family:Arial; font-size:8pt; color:#999999">
						<br/><br/><br/>												
						<% 
							if( usrLogin.getDemuna().getKydemuna() != null )
							{
								out.print("Usuario[ Nivel ] : "+ usrLogin.getUsuario() +" [ ");
								
								if( usrLogin.getNivel() == 1)
									{
									out.print("Administrador de Sistema ] <br/>");
									}
								else
									{
									out.print("Operador de Sistema ] <br/>");
									}																									
								out.print("Nombre Demuna    : [ "+usrLogin.getDemuna().getKydemuna()+" ] "+demunaLogin.getDireccion()+" <br/>");
								out.print("Numero Registro  : "+demunaLogin.getMimdesnro()+" <br/>"); 
								out.print("Direcci&oacute;n  : "+demunaLogin.getDireccion()+" <br/>"); 																																	
							}
						%>
					</div>				
					<div id="toolbar"></div>				
			</div>
		 <div id="DivformExpediente" <%if(pgn.equals("xclyear")) {%>  style="overflow:auto;" <%}%> >						
						<%
							if( usrLogin.getDemuna().getKydemuna() != null ){
								if(pgn.equals("acces")){																							
									out.print("<center><br/><br/><br/>Sistema de Registros de Expedientes Demunas 2013 <br/> <b>DemSys</b> Vrs. 2.0.0 (Beta)</center>");
								}	
							}
						
							if(pgn.equals("xclyear")) {%>							
								<jsp:include page="/reporteAnualExcel.htm"/>
							<% } %>															
			</div>	
			
			<div id="borde" style="padding:10px;">
					<div align="center">
						<img src="${pageContext.request.contextPath}/logos/logo1.png" width="99" height="28">
						<img src="${pageContext.request.contextPath}/logos/logo2.png" width="47" height="43">
						<img src="${pageContext.request.contextPath}/logos/logo3.png" width="141" height="31">
						<img src="${pageContext.request.contextPath}/logos/logo4.png" width="80" height="36">					 
					</div>
			</div>							
			<%
					pgn = request.getParameter("pagin");
					if( usrLogin.getDemuna().getKydemuna() != null)
							{
								{																							
									if( pgn.equals("expdts")){
									%>												
										<script type="text/javascript" src="${pageContext.request.contextPath}/js/formexpedientes.js"></script>																									
										<script type="text/javascript">
												AbrirExpedientes();
										</script>
									<%}	
									if( pgn.equals("slctds")){
									%>
										<script type="text/javascript" src="${pageContext.request.contextPath}/js/formsolicitudes.js"></script>
										<script type="text/javascript" src="${pageContext.request.contextPath}/js/formsolicitudexpedientes.js"></script>	
																						
										<script type="text/javascript">
												AbrirExpedientes();
										</script>
									<%}
									if( pgn.equals("ntrvsts")){
									%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formactores.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formentrevistas.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formentrevistasexpedientes.js"></script>	
																								
												<script type="text/javascript">
														AbrirExpedientes();
												</script>
											<%}
											if( pgn.equals("cnclcn")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formactores.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formconciliaciones.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formconciliacionesexpedientes.js"></script>	
																								
												<script type="text/javascript">
														AbrirExpedientes();
												</script>
											<%	}									
											if( pgn.equals("cmprms")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formactores.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formcompromiso.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formcompromisoexpedientes.js"></script>	
																								
												<script type="text/javascript">
														AbrirExpedientes();
												</script>
											<%}
											if( pgn.equals("vrfcn")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formactores.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formverificacion.js"></script>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formverificacionexpedientes.js"></script>	
																								
												<script type="text/javascript">
														AbrirExpedientes();
												</script>
											<% }
									//INICIO GRAFICOS																					
											if( pgn.equals("ocrsdmn")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/agrafdemuna.js"></script>
											<%}
									//FIN GRAFICOS			
									//INICIO REPORTES		
											if( pgn.equals("rptdmn")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/aarepordemunas.js"></script>
												<script type="text/javascript">
														AbrirExpedientes();
												</script>
											<%}
											if( pgn.equals("rptalldmn")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/aarepordemunasall.js"></script>
												<script type="text/javascript">
														AbrirExpedientes();
												</script>
											<%}
											if( pgn.equals("rptdmnmss"	)){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/aarepordemunasmeses.js"></script>												
											<%}
											if( pgn.equals("rptdmnmssmtr")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/aarepordemunasmesesmaterias.js"></script>												
											<%}		
									//FIN REPORTES						
											if( pgn.equals( "dmns")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/formdemunas.js"></script>													
											<%}
											if( pgn.equals( "nwusrs")){
											%>													
													<script type="text/javascript" src="${pageContext.request.contextPath}/js/formusuarios.js"></script>																																					
											<%}												
											if( pgn.equals( "usrs")){
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/frmusuariosupdate.js"></script>	
												<script type="text/javascript">
													abrirUsuarioUpdate();
												</script>
											<%}
											if( pgn.equals( "mtrs")){
											%>													
													<script type="text/javascript" src="${pageContext.request.contextPath}/js/formmaterias.js"></script>																																					
											<%}
											if( pgn.equals( "dscp")){
											%>													
													<script type="text/javascript" src="${pageContext.request.contextPath}/js/formdiscapacidades.js"></script>																																					
											<%}																						
											if( pgn.equals( "close")){
											//session_start(); 
											//session_unset(); 
											//session_destroy();		
											%>
												<script type="text/javascript" src="${pageContext.request.contextPath}/js/frmlogin.js"></script>	
												<script type="text/javascript">
														abrirLogin();
												</script>
											<%}										
									 
								}
							}					
				%>	
																			
		</body>
</html>