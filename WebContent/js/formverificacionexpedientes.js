var winExpediente, clavederegistro, clavederegistroVerificacion;

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONTROLES PARA LA BUSQUEDA
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var lblFiltrar = new Ext.form.Label({
	text : 'Buscar en',
	style : 'font-size:8pt;margin-right:10px;',
	height : 20,
	cls : 'x-label'
});

var lblFiltrara = new Ext.form.Label({
	text : 'el valor',
	style : 'font-size:8pt;margin-right:10px;margin-left:10px;',
	height : 20,
	cls : 'x-label'
});
var txtFiltrarEspec = new Ext.form.TextField({
	id : 'txtFiltrarEspec',
	name : 'txtFiltrarEspec',
	style : 'font-weight:bold;font-size:10pt;color:#FF6600',
	blankText : 'Ingrese valor a buscar.',
	width : 150
});

var quickSearchOprStoreEspec = new Ext.data.SimpleStore({
	fields : [ 'id1', 'dispval1' ],
	data : [ [ 'KyExpediente', 'Id' ],
			[ 'NroExpediente', 'Nro de Expediente' ],
			[ 'ResumenHechos', 'Hechos' ], [ 'Acciones', 'Acciones' ],
			[ 'EstOp', 'Estado Op.' ] ]
});
quickSearchOprStoreEspec.on('loadexception', function() {
	Ext.MessageBox.alert('Error',
			'Error al cargar los datos del los campos de busqueda');
});

var comboFiltroEspec = new Ext.form.ComboBox({
	name : 'comboFiltroEspec',
	id : 'comboFiltroEspec',
	store : quickSearchOprStoreEspec,
	forceSelection : true,
	displayField : 'dispval1',
	valueField : 'id1',
	hiddenName : 'rep',
	typeAhead : true,
	mode : 'local',
	triggerAction : 'all',
	selectOnFocus : true,
	width : 150,
	editable : false
});

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID PRINCIPAL
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var storeEliminarVerificacion = new Ext.data.JsonStore({
	url : 'formverificacion/eliminarverificacion.htm'
});

var btnEliminarVerificacion = new Ext.Button({
	id : 'btnEliminarVerificacion',
	width : 100,
	iconAlign : 'top',
	text : 'Eliminar',
	icon : 'icon/eliminar.gif',
	minWidth : 80,
	disabled : true,
	handler : function() {
		storeEliminarVerificacion.load({
			params : {
				KyVerificacion : clavederegistroVerificacion
			},
			callback : function() {
				estoreActas.load({
					params : {
						expediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

var btnEditarVerificacion = new Ext.Button({
	id : 'btnEditarVerificacion',
	x : 240,
	y : 470,
	width : 100,
	iconAlign : 'top',
	text : 'Editar',
	icon : 'icon/editar.png',
	minWidth : 80,
	disabled : true,
	handler : function() {
		AbrirSolictudes();
	}
});
// url:'formexpedientes.php?cargar=true&demuna='+txtKyDemunaIni.getValue(),
var estoreEspec = new Ext.data.JsonStore({
	url : 'formexpedientes/cargar.htm',
	root : 'dataEspec',
	totalProperty : 'total',
	paramNames : {
		start : 'offset', // The parameter name which specifies the start row
		limit : 'size', // The parameter name which specifies number of rows to
		// return
		sort : 'sort', // The parameter name which specifies the column to sort
		// on
		dir : 'dir' // The parameter name which specifies the sort direction
	},
	fields : [ 'KyDependencia', 'KyExpediente', 'NroExpediente', 'Fecha',
			'Lugar', 'Materia', 'ResumenHechos', 'Acciones', 'EstExp', 'EstOp',
			'Derivacion' ]
});
estoreEspec.load();

var btnBuscarYaEspec = new Ext.Button({
	id : 'btnBuscar',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Buscar',
	icon : 'icon/buscar.png',
	handler : function() {
		estoreEspec.reload({
			params : {
				campo : comboFiltroEspec.getValue(),
				valor : txtFiltrarEspec.getValue()
			}
		});
	}
});

function ftoprincipal(val) {
	return '<span style="color:#3366CC; font-weight:bold;">' + val + '</span>';
}
function ftosecundario(val) {
	return '<span style="color:#0099CC;">' + val + '</span>';
}
function ftogris(val) {
	return '<span style="color:#CCCCCC;">' + val + '</span>';
}
function ftoalingder(val) {
	return '<div style="text-align:right;">' + val + '</div>';
}
function ftoalingcen(val) {
	return '<div style="text-align:center;">' + val + '</div>';
}

function formatestado(val) {
	/* 0 Activo. 1. inactivo */
	if (val == 0) {
		return '<span style="color:#FF0000;font-size:11;"> ' + val
				+ ' : Inactivo</span>';
	}
	if (val == 1) {
		return '<span style="color:#999999;font-size:11;"> ' + val
				+ ' : Activo </span>';
	}
}
function formatosino(val) {
	/* 0 Activo. 1. inactivo */
	if (val == "SI") {
		return '<span style="color:#FF0000;font-size:11;">SI</span>';
	}
	if (val == "NO") {
		return '<span style="color:#999999;font-size:11;">NO</span>';
	}
}
function formatosexo(val) {
	if (val == 0) {
		return '<span style="color:#000;font-size:11;">M</span>';
	}
	if (val == 1) {
		return '<span style="color:#000;font-size:11;">F</span>';
	}
}

function formatoEstadoOp(val) {
	/* 0 Activo. 1. inactivo */
	if (val == 0) {
		return '<span style="color:#FFFFFF;background-color:#0066CC;font-size:11;"> '
				+ val + ' : Pendientes</span>';
	}
	if (val == 1) {
		return '<span style="color:#FF0000;background-color:#FFFF00;font-size:11;"> '
				+ val + ' : Desistidos </span>';
	}
	if (val == 2) {
		return '<span style="color:#CCCCCC;font-size:11;"> ' + val
				+ ' : Res.(Conciliacion) </span>';
	}
	if (val == 3) {
		return '<span style="color:#CCCCCC;font-size:11;"> ' + val
				+ ' : Res.(Gest.Admin.) </span>';
	}
	if (val == 4) {
		return '<span style="color:#CCCCCC;font-size:11;"> ' + val
				+ ' : Res.(Derivacion) </span>';
	}
}

var pager = new Ext.PagingToolbar({
	store : estoreEspec, // <--grid and PagingToolbar using same store
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
});

var myReaderEspec = new Ext.data.ArrayReader({}, [ {
	name : 'KyExpediente'
}, {
	name : 'KyDependencia'
}, {
	name : 'NroExpediente',
	type : 'string'
}, {
	name : 'Fecha'
}, {
	name : 'Materia'
}, {
	name : 'Lugar'
}, {
	name : 'ResumenHechos'
}, {
	name : 'EstExp'
}, ]);

var grid1 = new Ext.grid.GridPanel({
	frame : false,
	store : estoreEspec, // <--grid and PagingToolbar using same store
	reader : myReaderEspec,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyExpediente',
		width : 50,
		sortable : true,
		dataIndex : 'KyExpediente',
		hidden : true
	}, {
		header : 'KyDependencia',
		width : 50,
		sortable : true,
		dataIndex : 'KyDependencia',
		hidden : true
	}, {
		header : 'Nro Expediente',
		width : 100,
		renderer : ftosecundario,
		sortable : true,
		dataIndex : 'NroExpediente'
	}, {
		header : 'Fecha',
		width : 80,
		sortable : true,
		dataIndex : 'Fecha'
	}, {
		header : 'Materia',
		width : 120,
		sortable : true,
		dataIndex : 'Materia',
		hidden : true
	}, {
		header : 'Lugar',
		width : 150,
		sortable : true,
		renderer : ftogris,
		dataIndex : 'Lugar'
	}, {
		header : 'Hechos',
		width : 180,
		sortable : true,
		renderer : ftogris,
		dataIndex : 'ResumenHechos'
	}, {
		header : 'Acciones',
		width : 180,
		sortable : true,
		renderer : ftogris,
		dataIndex : 'Acciones'
	}, {
		header : 'Estado',
		width : 50,
		sortable : true,
		renderer : formatestado,
		dataIndex : 'EstExp'
	}, {
		header : 'Derivacion',
		width : 50,
		sortable : true,
		renderer : formatosino,
		dataIndex : 'Derivacion'
	}, {
		header : 'EstOp',
		width : 100,
		sortable : true,
		renderer : formatoEstadoOp,
		dataIndex : 'EstOp'
	}, ],
	viewConfig : {
		forceFit : true
	},
	AutoWidth : true,
	height : 220,
	frame : false,
	bbar : [ pager ],
	// tbar:['->',lblFiltrar,comboFiltroEspec,lblFiltrara,txtFiltrarEspec,btnBuscarYaEspec],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1.on('rowdblclick', this.editRegistro);
		grid1.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1, index, event) {
		var record = grid1.getStore().getAt(index);
		txtKyExpediente.setValue(record.get('KyExpediente'));
		TxtNroExpediente.setValue(record.get('NroExpediente'));
		txtLugar.setValue(record.get('Lugar'));
		txtFecha.setValue(record.get('Fecha'));
		clavederegistro = record.get('KyExpediente');
		estoreActas.load({
			params : {
				expediente : txtKyExpediente.getValue()
			}
		});

		btnEditarVerificacion.setDisabled(true);
		btnEliminarVerificacion.setDisabled(true);
	},
	editRegistro : function(grid1, index, event) {
		// var record = grid1.getStore().getAt(index);//PEDROJSABA

	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID Verificacion
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var estoreActas = new Ext.data.JsonStore({
	url : 'formverificacion/cargardatosverificacion.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyVerificacion', 'HoraInicio', 'HoraFin', 'MotVerificacion',
			'Autorizacion', 'DocIdentidad', 'Defensor', 'Nombres', 'Appaterno',
			'Apmaterno', 'Observacion', 'Concluciones', 'Fecha' ]

});

var pagerVerificacion = new Ext.PagingToolbar({
	store : estoreActas,
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
});

var myReaderVerificacion = new Ext.data.ArrayReader({}, [ {
	name : 'KyVerificacion'
}, {
	name : 'Autorizacion'
}, {
	name : 'Defensor'
}, {
	name : 'Nombres'
}, {
	name : 'Appaterno'
}, {
	name : 'Apmaterno'
}, {
	name : 'Concluciones',
	type : 'string'
}, ]);

var grid1Verificacion = new Ext.grid.GridPanel({
	title : 'Acta de Verificacion',
	frame : false,
	store : estoreActas,
	reader : myReaderVerificacion,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyVerificacion',
		width : 50,
		sortable : true,
		dataIndex : 'KyVerificacion',
		hidden : true
	}, {
		header : 'Autorizacion',
		width : 200,
		sortable : true,
		dataIndex : 'Autorizacion'
	}, {
		header : 'Defensor',
		width : 200,
		sortable : true,
		dataIndex : 'Defensor'
	},

	{
		header : 'Concluciones',
		width : 250,
		sortable : true,
		dataIndex : 'Concluciones'
	}, ],
	viewConfig : {
		forceFit : true
	},
	AutoWidth : true,
	height : 180,
	frame : false,
	bbar : [ pagerVerificacion, '->', btnEditarVerificacion,
			btnEliminarVerificacion ],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1Verificacion.on('rowdblclick', this.editRegistro);
		grid1Verificacion.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1Verificacion, index, event) {
		var record = grid1Verificacion.getStore().getAt(index);
		clavederegistroVerificacion = record.get('KyVerificacion');

		// ////////////////////// Datos de Solicitud ////////////////////
		txtKy.setValue(record.get('KyVerificacion'));
		TxtHoraInicio.setValue(record.get('HoraInicio'));
		TxtHoraFin.setValue(record.get('HoraFin'));
		TxtMotVerificacion.setValue(record.get('MotVerificacion'));
		TxtAutorizacion.setValue(record.get('Autorizacion'));
		TxtDocIdentidad.setValue(record.get('DocIdentidad'));
		TxtDefNombres.setValue(record.get('Nombres'));
		TxtDefAppaterno.setValue(record.get('Appaterno'));
		TxtDefApmaterno.setValue(record.get('Apmaterno'));
		TxtObservacion.setValue(record.get('Observacion'));
		TxtConcluciones.setValue(record.get('Concluciones'));
		txtFechaVerificacion.setValue(record.get('Fecha'));

		// ////////////////////// Datos de Solicitud ////////////////////
		btnEditarVerificacion.setDisabled(false);
		btnEliminarVerificacion.setDisabled(false);

	},
	editRegistro : function(grid1, index, event) {
		var record = grid1Verificacion.getStore().getAt(index);
		clavederegistroVerificacion = record.get('KyVerificacion');
		AbrirSolictudes();

	}

};

Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// FORMULARIO EXPEDIENTES ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var FrmExpediente = new Ext.FormPanel(
		{
			frame : true,
			layout : 'form',
			buttonAlign : 'right',
			tbar : [ '->', btnVerificacion ],
			items : [ grid1, grid1Verificacion ], // btnImagen
			tbar : [ lblFiltrar, comboFiltroEspec, lblFiltrara,
					txtFiltrarEspec, btnBuscarYaEspec, '->', btnVerificacion ],
			validarAccesoEspec : function() {
				if (this.getForm().isValid()) {
					this
							.getForm()
							.submit(
									{
										url : 'formexpedientes/eliminar.htm?id='
												+ txtRegActualEspec.value,
										method : 'POST',
										waitTitle : 'Conectando',
										waitMsg : 'Intentando eliminar...',
										success : function(form, action) {
											FrmExpediente.getForm().reset();
											FrmExpediente.getForm()
													.clearInvalid();
											estoreEspec.reload();
										},
										failure : function(form, action) {
											if (action.failureType == 'server') {
												var data = Ext.util.JSON
														.decode(action.response.responseText);
												Ext.Msg.alert(
														'Conexión Fallida',
														data.errors.reason,
														function() {
															// /
														});
											} else {
												Ext.Msg
														.alert(
																'Error!',
																'El servidor de autenticacion es inalcanzable : '
																		+ action.response.responseText);
											}
											FrmExpediente.getForm().reset();
										}
									});
				}
			}
		});

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// ABRIR VENTANA EXPEDIENTES
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

function AbrirExpedientes() {
	if (!winExpediente) {
		winExpediente = new Ext.Panel({
			renderTo : 'DivformExpediente',
			layout : 'fit',
			width : 1000,
			height : 500,
			title : '4. Acta de Verificacion ',
			resizable : false,
			closeAction : 'hide',
			closable : false,
			draggable : true,
			plain : false,
			border : false,
			modal : false,
			items : [ FrmExpediente ],
			listeners : {

			}
		});
	}
	winExpediente.show();
};

