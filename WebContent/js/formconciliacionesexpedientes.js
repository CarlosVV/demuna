var winExpediente, clavederegistro, clavederegistroConciliacion;

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

var storeEliminarConciliacion = new Ext.data.JsonStore({
	url : 'formconciliaciones/eliminarconciliacion.htm'// url:'formconciliaciones.php?eliminarconciliacion=true'
});

var btnEliminarConciliacion = new Ext.Button({
	id : 'btnEliminarConciliacion',
	width : 100,
	iconAlign : 'top',
	text : 'Eliminar',
	icon : 'icon/eliminar.gif',
	minWidth : 80,
	disabled : true,
	handler : function() {
		storeEliminarConciliacion.load({
			params : {
				KyActa : clavederegistroConciliacion
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

var btnEditarConciliacion = new Ext.Button({
	id : 'btnEditarConciliacion',
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

// url:'formexpedientes.php?cargar=true&demuna='+txtKyDemunaIni.getValue()
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
		estoreMaterias.load({
			params : {
				idexpediente : txtKyExpediente.getValue()
			}
		});

		btnEditarConciliacion.setDisabled(true);
		btnEliminarConciliacion.setDisabled(true);

		estoreMaterias.load({
			params : {
				idexpediente : txtKyExpediente.getValue()
			}
		});

	},
	editRegistro : function(grid1, index, event) {
		// var record = grid1.getStore().getAt(index);//@pedrojsaba

	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID CONCILIACION
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var estoreActas = new Ext.data.JsonStore({
	url : 'formconciliaciones/cargardatosconciliacion.htm', // +sessvars.idempresa
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyActa', 'Acuerdos', 'Seguimiento', 'Fecha', 'p1', 'p2', 'p3',
			'p4', 'p5', 'conciliacion' ]
});

var pagerConciliacion = new Ext.PagingToolbar({
	store : estoreActas,
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
});

var myReaderConciliacion = new Ext.data.ArrayReader({}, [ {
	name : 'KyActa'
}, {
	name : 'Acuerdos'
}, {
	name : 'Seguimiento',
	type : 'string'
}, ]);

var grid1Conciliacion = new Ext.grid.GridPanel({
	title : 'Acta de Conciliación',
	style : 'float:right;',
	frame : false,
	store : estoreActas,
	reader : myReaderConciliacion,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyActa',
		width : 50,
		sortable : true,
		dataIndex : 'KyActa',
		hidden : true
	}, {
		header : 'Conciliacion',
		width : 100,
		sortable : true,
		dataIndex : 'conciliacion',
		hidden : true
	}, {
		header : 'Fecha',
		width : 120,
		sortable : true,
		dataIndex : 'Fecha'
	}, {
		header : 'Acuerdos',
		width : 200,
		sortable : true,
		dataIndex : 'Acuerdos'
	}, {
		header : 'Seguimiento',
		width : 250,
		sortable : true,
		dataIndex : 'Seguimiento'
	}, ],
	viewConfig : {
		forceFit : true
	},
	width : 710,
	height : 400,
	frame : false,
	tbar : [ pagerConciliacion, '->', btnEditarConciliacion,
			btnEliminarConciliacion ],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1Conciliacion.on('rowdblclick', this.editRegistro);
		grid1Conciliacion.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1Conciliacion, index, event) {
		var record = grid1Conciliacion.getStore().getAt(index);
		clavederegistroConciliacion = record.get('KyActa');

		// ////////////////////// Datos de Entrevista ////////////////////
		txtKy.setValue(record.get('KyActa'));
		txtFechaConciliacion.setValue(record.get('Fecha'));
		TxtInforme.setValue(record.get('Acuerdos'));
		TxtOtros.setValue(record.get('Seguimiento'));

		chk01.setValue(record.get('p1'));
		chk02.setValue(record.get('p2'));
		chk03.setValue(record.get('p3'));
		chk04.setValue(record.get('p4'));
		chk05.setValue(record.get('p5'));
		var cncilia = record.get('conciliacion');

		if (cncilia == 1) {
			rdo01.setValue(true);
		}
		if (cncilia == 2) {
			rdo02.setValue(true);
		}
		if (cncilia == 3) {
			rdo03.setValue(true);
		}
		if (cncilia == 4) {
			rdo04.setValue(true);
		}
		if (cncilia == 5) {
			rdo05.setValue(true);
		}
		if (cncilia == 6) {
			rdo06.setValue(true);
		}
		// ////////////////////// Datos de Entrevista ////////////////////
		btnEditarConciliacion.setDisabled(false);
		btnEliminarConciliacion.setDisabled(false);

	},
	editRegistro : function(grid1, index, event) {
		var record = grid1Conciliacion.getStore().getAt(index);
		clavederegistroConciliacion = record.get('KyActa');
		AbrirSolictudes();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE

// url:'formexpedientes.php?cargarmateriasexpediente=true',
var estoreMaterias = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarmateriasexpediente.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset', // The parameter name which specifies the start row
		limit : 'size', // The parameter name which specifies number of rows to
		// return
		sort : 'sort', // The parameter name which specifies the column to sort
		// on
		dir : 'dir' // The parameter name which specifies the sort direction
	},
	fields : [ 'KyExpedienteMateria', 'KyExpediente', 'KyMateria', 'Materia',
			'Conciliable' ]
});

var pagerMater = new Ext.PagingToolbar({
	store : estoreMaterias, // <--grid and PagingToolbar using same store
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
});

var myReaderMater = new Ext.data.ArrayReader({}, [ {
	name : 'KyExpedienteMateria'
}, {
	name : 'KyExpediente'
}, {
	name : 'KyMateria'
} ]);

function formatoSINO(val) {
	if (val == "SI") {
		return '<span style="color:#000;font-size:11;">SI</span>';
	}
	if (val == "NO") {
		return '<span style="color:#FF0000;font-size:11;">NO</span>';
	}
}

var grid1Mater = new Ext.grid.EditorGridPanel({
	title : 'Materias',
	frame : false,
	store : estoreMaterias, // <--grid and PagingToolbar using same store
	reader : myReaderMater,
	style : 'float:left;',
	clicksToEdit : 1,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyMateria',
		width : 50,
		sortable : true,
		dataIndex : 'KyExpedienteMateria',
		hidden : true
	}, {
		header : 'Materia',
		width : 100,
		sortable : true,
		dataIndex : 'Materia'
	}, {
		header : 'Conciliable',
		width : 100,
		sortable : true,
		renderer : formatoSINO,
		dataIndex : 'Conciliable'
	} ],
	viewConfig : {
		forceFit : true
	},
	width : 270,
	height : 400,
	frame : false,
	tbar : [ pagerMater ],
	tbar : [],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1Mater.on('rowdblclick', this.editRegistro);
		grid1Mater.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1Mater, index, event) {
		var record = grid1Mater.getStore().getAt(index);
		clavederegistromaterias = record.get('KyExpedienteMateria');
	},
	editRegistro : function(grid1Mater, index, event) {
		var record = grid1Mater.getStore().getAt(index);
		clavederegistroinformantes = record.get('KyExpedienteMateria');
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE
// ////////////////////////// FIN GRID MATERIAS DEL EXPEDIENTE

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
			tbar : [ '->', btnConciliacion ],
			items : [ grid1, grid1Mater, grid1Conciliacion ], // btnImagen
			tbar : [ lblFiltrar, comboFiltroEspec, lblFiltrara,
					txtFiltrarEspec, btnBuscarYaEspec, '->', btnConciliacion ],
			validarAccesoEspec : function() {
				if (this.getForm().isValid()) {
					this
							.getForm()
							.submit(
									{
										url : 'formexpedientes/eliminar.htm',// 'formexpedientes.php?eliminar='+txtRegActualEspec.value,
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
			title : '4. Acta de Conciliacion ',
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

