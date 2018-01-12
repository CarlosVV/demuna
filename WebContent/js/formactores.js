var winNuevoActor;

var txtKyExpediente = new Ext.form.TextField({
	fieldLabel : 'Codigo',
	name : 'txtKyExpediente',
	readOnly : true,
	style : 'text-align: center',
	width : 40,
	value : '00'
});
// url:'formexpedientes.php?cargarinformantes=true&idempresa=',
// //+sessvars.idempresa
var estoreEspecInform = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarinformantes.htm',
	root : 'dataEspec',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyInformante', 'Dni', 'Nombre', 'Nombres', 'ApPaterno',
			'ApMaterno', 'Edad', 'Sexo', 'Domicilio', 'Telefono', 'Ocupacion',
			'Relacion_Afectado', 'KyDepartamento' ]
});

var myReaderEspecInform = new Ext.data.ArrayReader({}, [ {
	name : 'KyInformante'
}, {
	name : 'Dni'
}, {
	name : 'Nombre'
}, {
	name : 'Nombres'
}, {
	name : 'ApPaterno'
}, {
	name : 'ApMaterno'
}, {
	name : 'Domicilio'
}, {
	name : 'Edad'
}, {
	name : 'Sexo'
}, {
	name : 'Telefono'
}, {
	name : 'Ocupacion'
}, {
	name : 'Relacion_Afectado'
}, {
	name : 'KyDepartamento'
}, ]);

var grid1Inform = new Ext.grid.GridPanel({
	frame : false,
	store : estoreEspecInform, // <--grid and PagingToolbar using same store
	reader : myReaderEspecInform,
	clicksToEdit : 1,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyInformante',
		width : 50,
		sortable : true,
		dataIndex : 'KyInformante',
		hidden : true
	}, {
		header : 'Ubigeo',
		width : 100,
		sortable : true,
		dataIndex : 'KyDepartamento'
	}, // , editor: comboDepartamentos
	{
		header : 'Dni',
		width : 80,
		sortable : true,
		dataIndex : 'Dni',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Nombre',
		width : 150,
		sortable : true,
		dataIndex : 'Nombre',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Nombres',
		width : 150,
		sortable : true,
		dataIndex : 'Nombres',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'ApPaterno',
		width : 150,
		sortable : true,
		dataIndex : 'ApPaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'ApMaterno',
		width : 150,
		sortable : true,
		dataIndex : 'ApMaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'Domicilio',
		width : 180,
		sortable : true,
		dataIndex : 'Domicilio',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Edad',
		width : 40,
		sortable : true,
		dataIndex : 'Edad',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Sexo',
		width : 40,
		sortable : true,
		dataIndex : 'Sexo',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Telefono',
		width : 60,
		sortable : true,
		dataIndex : 'Telefono',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Ocupacion',
		width : 100,
		sortable : true,
		dataIndex : 'Ocupacion',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Relacion Con Afectado',
		width : 150,
		sortable : true,
		dataIndex : 'Relacion_Afectado',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, ],
	viewConfig : {
		forceFit : true
	},
	autoWidth : true,
	height : 190,
	frame : false,
	tbar : [ '->' ],
	tbar : [],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1Inform.on('rowdblclick', this.editRegistro);
		grid1Inform.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1Inform, index, event) {
		var record = grid1Inform.getStore().getAt(index);
		clavederegistroinformantes = record.get('KyInformante');
		txtEntNombres.setValue(record.get('Nombres'));
		txtEntAppaterno.setValue(record.get('ApPaterno'));
		txtEntApmaterno.setValue(record.get('ApMaterno'));
		TxtTipo.setValue('Informante');
		txtEntrevistadoDireccion.setValue(record.get('Domicilio'));
		txtEntrevistadoDni.setValue(record.get('Dni'));
		txtEntrevistadoEdad.setValue(record.get('Edad'));
		txtEntrevistadoSexo.setValue(record.get('Sexo'));
	},
	editRegistro : function(grid1Inform, index, event) {
		var record = grid1Inform.getStore().getAt(index);
		clavederegistroinformantes = record.get('KyInformante');
		winNuevoActor.hide();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID AFECTADOS
// //////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

// url:'formexpedientes.php?cargarafectados=true&idempresa=',
// //+sessvars.idempresa
var estoreEspecAfect = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarafectados.htm',
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
	fields : [ 'KyAfectado', 'Dni', 'Nombre', 'Nombres', 'ApPaterno',
			'ApMaterno', 'Domicilio', 'Edad', 'Sexo', 'KyDepartamento' ]
});

var myReaderEspecAfect = new Ext.data.ArrayReader({}, [ {
	name : 'KyAfectado'
}, {
	name : 'Dni'
}, {
	name : 'Nombre'
}, {
	name : 'Nombres'
}, {
	name : 'ApPaterno'
}, {
	name : 'ApMaterno'
}, {
	name : 'Domicilio'
}, {
	name : 'Edad'
}, {
	name : 'Sexo'
}, {
	name : 'KyDepartamento'
}, ]);

var grid1Afect = new Ext.grid.GridPanel({
	frame : false,
	store : estoreEspecAfect, // <--grid and PagingToolbar using same store
	reader : myReaderEspecAfect,
	clicksToEdit : 1,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyAfectado',
		width : 50,
		sortable : true,
		dataIndex : 'KyAfectado',
		hidden : true
	}, {
		header : 'Ubigeo',
		width : 100,
		sortable : true,
		dataIndex : 'KyDepartamento'
	}, {
		header : 'Dni',
		width : 80,
		sortable : true,
		dataIndex : 'Dni',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Nombre',
		width : 150,
		sortable : true,
		dataIndex : 'Nombre',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Nombres',
		width : 150,
		sortable : true,
		dataIndex : 'Nombres',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'ApPaterno',
		width : 150,
		sortable : true,
		dataIndex : 'ApPaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'ApMaterno',
		width : 150,
		sortable : true,
		dataIndex : 'ApMaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'Domicilio',
		width : 180,
		sortable : true,
		dataIndex : 'Domicilio',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Edad',
		width : 40,
		sortable : true,
		dataIndex : 'Edad',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Sexo',
		width : 40,
		sortable : true,
		dataIndex : 'Sexo',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, ],
	viewConfig : {
		forceFit : true
	},
	autoWidth : true,
	height : 190,
	frame : false,
	tbar : [],
	// tbar:['->',lblFiltrar,comboFiltroEspec,lblFiltrara,txtFiltrarEspec,btnBuscarYaEspec],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1Afect.on('rowdblclick', this.editRegistro);
		grid1Afect.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1Afect, index, event) {
		var record = grid1Afect.getStore().getAt(index);
		clavederegistroafectados = record.get('KyAfectado');
		txtEntNombres.setValue(record.get('Nombres'));
		txtEntAppaterno.setValue(record.get('ApPaterno'));
		txtEntApmaterno.setValue(record.get('ApMaterno'));
		TxtTipo.setValue('Afectado');
		txtEntrevistadoDireccion.setValue(record.get('Domicilio'));
		txtEntrevistadoDni.setValue(record.get('Dni'));
		txtEntrevistadoEdad.setValue(record.get('Edad'));
		txtEntrevistadoSexo.setValue(record.get('Sexo'));
	},
	editRegistro : function(grid1Inform, index, event) {
		var record = grid1Afect.getStore().getAt(index);
		clavederegistroafectados = record.get('KyAfectado');
		winNuevoActor.hide();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID TRANSGRESORES
// //////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

// url:'formexpedientes.php?cargartransgresor=true&idempresa=',
// //+sessvars.idempresa
var estoreEspecTrans = new Ext.data.JsonStore({
	url : 'formexpedientes/cargartransgresor.htm',
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
	fields : [ 'KyTransgresor', 'Dni', 'Nombre', 'Nombres', 'ApPaterno',
			'ApMaterno', 'Domicilio', 'Edad', 'Sexo', 'KyDistrito',
			'KyDepartamento', 'KyProvincia' ]
});

var myReaderEspecTrans = new Ext.data.ArrayReader({}, [ {
	name : 'KyTransgresor'
}, {
	name : 'Dni'
}, {
	name : 'Nombre'
}, {
	name : 'Nombres'
}, {
	name : 'ApPaterno'
}, {
	name : 'ApMaterno'
}, {
	name : 'Domicilio'
}, {
	name : 'Edad'
}, {
	name : 'Sexo'
}, ]);

var grid1Trans = new Ext.grid.GridPanel({
	frame : false,
	store : estoreEspecTrans, // <--grid and PagingToolbar using same store
	reader : myReaderEspecTrans,
	clicksToEdit : 1,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyTransgresor',
		width : 50,
		sortable : true,
		dataIndex : 'KyTransgresor',
		hidden : true
	}, {
		header : 'Ubigeo',
		width : 100,
		sortable : true,
		dataIndex : 'KyDepartamento'
	}, {
		header : 'Dni',
		width : 80,
		sortable : true,
		dataIndex : 'Dni',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Nombre',
		width : 150,
		sortable : true,
		dataIndex : 'Nombre',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Nombres',
		width : 150,
		sortable : true,
		dataIndex : 'Nombres',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'ApPaterno',
		width : 150,
		sortable : true,
		dataIndex : 'ApPaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'ApMaterno',
		width : 150,
		sortable : true,
		dataIndex : 'ApMaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		},
		hidden : true
	}, {
		header : 'Domicilio',
		width : 180,
		sortable : true,
		dataIndex : 'Domicilio',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Edad',
		width : 40,
		sortable : true,
		dataIndex : 'Edad',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Sexo',
		width : 40,
		sortable : true,
		dataIndex : 'Sexo',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, ],
	viewConfig : {
		forceFit : true
	},
	autoWidth : true,
	height : 190,
	frame : false,
	tbar : [],
	border : false,
	stripeRows : true,
	loadMask : true
});

/** ********FUNCION LEER DOBLE CLICK********** */
Ext.ns('clickgrid');
clickgrid.gridclik = {
	init : function() {
		grid1Trans.on('rowdblclick', this.editRegistro);
		grid1Trans.on('rowclick', this.editMovie);
	},
	editMovie : function(grid1Trans, index, event) {
		var record = grid1Trans.getStore().getAt(index);
		clavederegistrotransgresor = record.get('KyTransgresor');
		txtEntNombres.setValue(record.get('Nombres'));
		txtEntAppaterno.setValue(record.get('ApPaterno'));
		txtEntApmaterno.setValue(record.get('ApMaterno'));
		TxtTipo.setValue('Transgresor');
		txtEntrevistadoDireccion.setValue(record.get('Domicilio'));
		txtEntrevistadoDni.setValue(record.get('Dni'));
		txtEntrevistadoEdad.setValue(record.get('Edad'));
		txtEntrevistadoSexo.setValue(record.get('Sexo'));
	},
	editRegistro : function(grid1Trans, index, event) {
		var record = grid1Trans.getStore().getAt(index);
		clavederegistrotransgresor = record.get('KyTransgresor');
		winNuevoActor.hide();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

var paneltabs = new Ext.TabPanel({
	name : 'paneltabs',
	activeTab : 0,
	border : false,
	enableTabScroll : true,
	defaults : {
		height : 500,
		bodyStyle : 'padding:10px;background-color:#fff'
	},
	items : [ {
		title : 'Informante(s)',
		layout : 'form',
		items : [ grid1Inform ]
	}, {
		title : 'Afectado(s)/Beneficiario(s)',
		layout : 'form',
		items : [ grid1Afect ]
	}, {
		title : 'Transgresor(s)/Obligado(s)',
		layout : 'form',
		labelAlign : 'top',
		items : [ grid1Trans ]
	} ]
});

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// FORMULARIO NUEVO/EDITAR ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var FrmnuevoExpediente = new Ext.FormPanel(
		{
			frame : false,
			layout : 'form',
			bodyStyle : 'padding: 10px;',
			buttonAlign : 'right',
			items : [ paneltabs ],
			validarAccesoEspec : function() {
				if (this.getForm().isValid()) {
					this
							.getForm()
							.submit(
									{
										url : 'formexpedientes/nuevo.htm',// 'formexpedientes.php?nuevo=true&idempresa=1',//sessvars.idempresa,
										method : 'POST',
										waitTitle : 'Conectando',
										waitMsg : 'Intentando guardar el registro...',
										success : function(form, action) {

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
												var xmierror = action.result.mierror;
												var xmsg = action.result.msg;
												if (xmierror == 1) {
													Ext.Msg
															.alert(
																	'Validaci&oacute;n',
																	xmsg);
												} else {
													Ext.Msg
															.alert(
																	'Error!',
																	'El servidor de autenticacion es inalcanzable : '
																			+ action.response.responseText);
													FrmnuevoExpediente
															.getForm().reset();
												}
											}

										}
									});
				}
			}

		});

var btnCerrarActores = new Ext.Button({
	id : 'btnCerrarActores',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Cerrar',
	icon : './icon/cerrar.png',
	handler : function() {
		winNuevoActor.hide();
	}
});

function AbrirBuscarActor() {
	if (!winNuevoActor) {
		winNuevoActor = new Ext.Window({
			layout : 'fit',
			title : 'Seleccionar Entrevistado',
			width : 800,
			height : 400,
			resizable : false,
			closeAction : 'hide',
			closable : true,
			draggable : true,
			plain : true,
			border : false,
			modal : true,
			style : 'margin:10px;',
			items : [ FrmnuevoExpediente ],
			tbar : [ '->', btnCerrarActores ],
			listeners : {
				show : function() {
					estoreEspecInform.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
					estoreEspecAfect.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
					estoreEspecTrans.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}

			}
		});
	}
	winNuevoActor.show();
}