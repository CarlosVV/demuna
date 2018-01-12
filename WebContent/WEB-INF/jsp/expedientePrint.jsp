<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="pe.gob.dmn.model.ExpedientesTransgresor"%>
<%@page import="pe.gob.dmn.model.ExpedientesAfectado"%>
<%@page import="pe.gob.dmn.model.ExpedientesInformante"%>
<%@page import="pe.gob.dmn.model.ExpedientesMateria"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:useBean id="expediente" scope="request"
	class="pe.gob.dmn.model.Expediente" />
<jsp:useBean id="usrLogin" scope="session"
	class="pe.gob.dmn.model.Usuario" />
<jsp:useBean id="demunaLogin" scope="session"
	class="pe.gob.dmn.model.Demuna" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/vistaprevia.css" />
<title>Imprimir Expediente</title>
</head>

<body>	
	<div id="contenedor">
		<input type="button" onclick="print();" value="Imprimir" />
		<div id="datosder">

			Nombre Demuna : [
			<%=demunaLogin.getKydemuna()%>
			]
			<%=demunaLogin.getNombre()%>
			<br /> Numero Registro :
			<%=demunaLogin.getMimdesnro()%>
			<br /> Nro. Exp. : <%= String.format("%09d", expediente.getKyexpediente()) %>
			<br /> Estado :
			<%=expediente.getEstexp()%>
			<br /> Fecha :
			<%=expediente.getFecha()%>
			<br />
		</div>

		<div id="datosizq">

			Lugar :
			<%=expediente.getLugar()%><br />
		</div>

		<br />

		<div id="datosborde">
			<table width="300" border="0" class="colorlinea">
				<%
					List<ExpedientesMateria> lExpMat = (List<ExpedientesMateria>) request
							.getAttribute("expedienteMaterias");
				%>
				<tr class="colorlinea">
					<td class="cabecera">Materia(s): <%=lExpMat.size()%></td>
				</tr>
				<%
					for (ExpedientesMateria ex : lExpMat) {
				%>
				<tr class="colorlinea">
					<td><%=ex.getMateria() != null ? ex.getMateria()
						.getMateria() : ""%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>

		<br />

		<div id="datosborde">
			<%
				List<ExpedientesInformante> lExpInf = (List<ExpedientesInformante>) request
						.getAttribute("expedienteInformantes");
			%>
			Informante(s):
			<%=lExpInf.size()%>
			<table width="830" border="0" class="colorlinea">
				<tr class="colorlinea">
					<td class="cabecera">Dni</td>
					<td class="cabecera">Nombre</td>
					<td class="cabecera">Domicilio</td>
					<td class="cabecera">Edad</td>
					<td class="cabecera">Sexo</td>
					<td class="cabecera">Telefono</td>
					<td class="cabecera">Ocupacion</td>
					<td class="cabecera">Relacion_Afectado</td>
				</tr>
				<%
					for (ExpedientesInformante exIn : lExpInf) {
				%>
				<tr class="colorlinea">
					<td><%=exIn.getDni() != null ? exIn.getDni() : ""%></td>
					<td><%=exIn.getNombre() != null ? exIn.getNombre() : ""%></td>
					<td><%=exIn.getDomicilio() != null ? exIn.getDomicilio()
						: ""%></td>
					<td><%=exIn.getEdad()%></td>
					<td><%=exIn.getSexo() != null ? exIn.getSexo() : ""%></td>
					<td><%=exIn.getTelefono() != null ? exIn.getTelefono() : ""%></td>
					<td><%=exIn.getOcupacion() != null ? exIn.getOcupacion()
						: ""%></td>
					<td><%=exIn.getRelacionAfectado() != null ? exIn
						.getRelacionAfectado() : ""%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>

		<br />

		<div id="datosborde">
			<%
				List<ExpedientesAfectado> lExpAfe = (List<ExpedientesAfectado>) request
						.getAttribute("expedienteAfectados");
			%>
			Afectado(s)/Beneficiario(s):
			<%=lExpAfe.size()%>
			<table width="830" border="0" class="colorlinea">
				<tr class="colorlinea">
					<td class="cabecera">Dni</td>
					<td class="cabecera">Nombre</td>
					<td class="cabecera">Domicilio</td>
					<td class="cabecera">Edad</td>
					<td class="cabecera">Sexo</td>
					<td class="cabecera">Discapacidad</td>
					<td class="cabecera">Gestante</td>
				</tr>
				<%
					for (ExpedientesAfectado exAf : lExpAfe) {
				%>
				<tr class="colorlinea">
					<td><%=exAf.getDni() != null ? exAf.getDni() : ""%></td>
					<td><%=exAf.getNombre() != null ? exAf.getNombre() : ""%></td>
					<td><%=exAf.getDomicilio() != null ? exAf.getDomicilio()
						: ""%></td>
					<td><%=exAf.getEdad()%></td>
					<td><%=exAf.getSexo() != null ? exAf.getSexo() : ""%></td>
					<td><%=exAf.getDiscapacidad() != null ? exAf
						.getDiscapacidad() : ""%></td>
					<td><%=exAf.getMadresoltera() != null ? exAf
						.getMadresoltera() : ""%></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>

		<br />

		<div id="datosborde">
			<%
				List<ExpedientesTransgresor> lExpTra = (List<ExpedientesTransgresor>) request
						.getAttribute("expedienteTransgresor");
			%>
			Transgresor(es)/Obligado(s):
			<%=lExpTra.size()%>
			<table width="830" border="0" class="colorlinea">
				<tr class="colorlinea">
					<td class="cabecera">Dni</td>
					<td class="cabecera">Nombre</td>
					<td class="cabecera">Domicilio</td>
					<td class="cabecera">Edad</td>
					<td class="cabecera">Sexo</td>
					<td class="cabecera">Ocupacion</td>
					<td class="cabecera">Relacion_Afectado</td>
				</tr>

				<%
					for (ExpedientesTransgresor exTr : lExpTra) {
				%>
				<tr class="colorlinea">
					<td><%=exTr.getDni() != null ? exTr.getDni() : ""%></td>
					<td><%=exTr.getNombre() != null ? exTr.getNombre() : ""%></td>
					<td><%=exTr.getDomicilio() != null ? exTr.getDomicilio()
						: ""%></td>
					<td><%=exTr.getEdad()%></td>
					<td><%=exTr.getSexo() != null ? exTr.getSexo() : ""%></td>
					<td><%=exTr.getOcupacion() != null ? exTr.getOcupacion()
						: ""%></td>
					<td><%=exTr.getRelacion() != null ? exTr.getRelacion() : ""%></td>
				</tr>
				<%
					}
				%>


			</table>
		</div>

		<div id="datos">
			<br />Resumen de los Hechos
			<div id="datosborde">
				<%=expediente.getResumenhechos()!=null?expediente.getResumenhechos():""%><br /> <br />
			</div>
		</div>
		<div id="datos">
			<br />Acciones a Realizar
			<div id="datosborde">
				<%=expediente.getAcciones()!=null?expediente.getAcciones():""%>
			</div>
		</div>

	</div>

</body>
</html>
