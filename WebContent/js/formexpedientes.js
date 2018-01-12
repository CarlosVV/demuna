var winExpediente, winUbigeo, winNuevoExpediente, winBuscarExpediente, winCargarImagen, clavederegistro, clavederegistroinformantes, clavederegistroafectados, clavederegistrotransgresor;
var clavederegistromaterias, winVista;

var dataEstadoCivil = [ 'Soltero(a)', 'Casado(a)', 'Conviviente',
		'Divorciado(a)', 'Viudo(a)' ];
var cboEstadoCivil = new Ext.form.ComboBox({
	name : 'cboEstadoCivil',
	forceSelection : true,
	allowBlank : false,
	store : dataEstadoCivil,
	triggerAction : 'all',
	editable : false,
	value : 'NO'
});

var cboEstadoCivil1 = new Ext.form.ComboBox({
	name : 'cboEstadoCivil1',
	forceSelection : true,
	allowBlank : false,
	store : dataEstadoCivil,
	triggerAction : 'all',
	editable : false,
	value : 'NO'
});

var cboEstadoCivil2 = new Ext.form.ComboBox({
	name : 'cboEstadoCivil2',
	forceSelection : true,
	allowBlank : false,
	store : dataEstadoCivil,
	triggerAction : 'all',
	editable : false,
	value : 'NO'
});

clavederegistro = 1;
var btnPrintExpediente = new Ext.Button({
	id : 'btnPrintExpediente',
	iconAlign : 'top',
	text : 'Imprimir Expediente',
	icon : './icon/preview.png',
	minWidth : 80,
	handler : function() {
		window.open('expedientePrint.htm?xfile=' + clavederegistro);
	}
});

function AbrirVista(xfile) {
	if (!winVista) {
		winVista = new Ext.Window({
			layout : 'form',
			bodyStyle : 'padding: 10px;background-color:#FFFFFF;',
			title : 'Vista Previa',
			autoLoad : 'expedientePrint.htm?xfile=' + xfile,
			maximized : true,
			resizable : false,
			closeAction : 'hide',
			closable : true,
			listeners : {
				show : function() {
				}
			}
		});
	}
	winVista.show();
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONTROLES PARA NUEVO/EDITAR FORMULARIO
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

// //////////////////////METARIAS DE LOS CASOS
var storematerias = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarMaterias.htm?estado=1',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyMateria', 'Materia' ]
});

var combomateria = new Ext.form.ComboBox({
	fieldLabel : 'Materia',
	name : 'combomateria',
	forceSelection : true,
	store : storematerias,
	hiddenName : 'combomateriaid',
	emptyText : 'Seleccione Materia',
	triggerAction : 'all',
	editable : false,
	displayField : 'Materia',
	valueField : 'KyMateria',
	allowBlank : false,
	blankText : 'Seleccione Materia',
	width : 350
});

var storeNuevaMateria = new Ext.data.JsonStore({
	url : 'formexpedientes/newmateria.htm'
});

var btnInsertarMateria = new Ext.Button({
	id : 'btnInsertarMateria',
	width : 50,
	heigth : 40,
	icon : './icon/nuevo.png',
	handler : function() {
		var fecha = txtFecha.value;
		storeNuevaMateria.load({
			params : {
				KyExpediente : txtKyExpediente.getValue(),
				KyMateria : combomateria.getValue(),
				KyDependencia : txtKyDemunaIni.getValue(),
				Fecha : fecha
			},
			callback : function() {
				estoreMaterias.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

var storeEliminarMateria = new Ext.data.JsonStore({
	url : 'formexpedientes/delmateria.htm'
});

var btnEliminarMateria = new Ext.Button({
	id : 'btnEliminarMateria',
	width : 100,
	iconAlign : 'top',
	icon : './icon/eliminar.gif',
	minWidth : 80,
	handler : function() {
		storeEliminarMateria.load({
			params : {
				KyExpedienteMateria : clavederegistromaterias
			},
			callback : function() {
				estoreMaterias.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

// /////////////////////////////FIN METARIAS DE LOS CASOS

// /////////////////////////// GRID MATERIAS DEL EXPEDIENTE

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
	frame : false,
	store : estoreMaterias, // <--grid and PagingToolbar using same store
	reader : myReaderMater,
	clicksToEdit : 1,
	columns : [ new Ext.grid.RowNumberer(), {
		header : 'KyMateria',
		width : 50,
		sortable : true,
		dataIndex : 'KyExpedienteMateria',
		hidden : true
	}, {
		header : 'Materia',
		width : 80,
		sortable : true,
		dataIndex : 'Materia'
	}, {
		header : 'Conciliable?',
		width : 300,
		sortable : true,
		renderer : formatoSINO,
		dataIndex : 'Conciliable'
	} ],
	viewConfig : {
		forceFit : true
	},
	autoWidth : true,
	height : 250,
	frame : false,
	tbar : [ pagerMater, '->', btnEliminarMateria, combomateria,
			btnInsertarMateria ],
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

// ////////////////////////// FIN GRID MATERIAS DEL EXPEDIENTE

var txtKyExpediente = new Ext.form.TextField({
	fieldLabel : 'Codigo',
	name : 'txtKyExpediente',
	readOnly : true,
	style : 'text-align: center',
	width : 40,
	value : '00'
});

var txtdependencia = new Ext.form.Hidden({
	fieldLabel : 'Dependencia',
	name : 'txtdependencia',
	readOnly : true,
	style : 'text-align: center',
	width : 40,
	value : '00'
});

var TxtNroExpediente = new Ext.form.TextField({
	fieldLabel : 'Nro Expediente',
	name : 'TxtNroExpediente',
	style : 'text-align: center',
	width : 200
});

var txtFecha = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	format : 'd-m-Y', // 'd mmm yyyy' is not valid, but I assume this is
	// close?
	width : 120,
	name : 'txtFecha'

});

var chk01 = new Ext.form.Checkbox({
	fieldLabel : 'Orientacion',
	name : 'chk01'
});

var chk02 = new Ext.form.Checkbox({
	fieldLabel : 'Derivacion',
	name : 'chk02'
});

var chk03 = new Ext.form.Checkbox({
	fieldLabel : 'Denuncia',
	name : 'chk03'
});
var chk04 = new Ext.form.Checkbox({
	fieldLabel : 'Conc.Extrajudicial',
	name : 'chk04'
});

var chk05 = new Ext.form.Checkbox({
	fieldLabel : 'Compromiso',
	name : 'chk05'
});

var chk06 = new Ext.form.Checkbox({
	fieldLabel : 'Gest.Admin.',
	name : 'chk06'
});

var fieldIzquierda1 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 140,
	height : 40,
	items : [ chk01 ]
});

var fieldIzquierda2 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 140,
	height : 40,
	items : [ chk02 ]
});

var fieldIzquierda3 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 140,
	height : 40,
	items : [ chk03 ]
});

var fieldIzquierda4 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 140,
	height : 40,
	items : [ chk04 ]
});

var fieldIzquierda5 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 140,
	height : 40,
	items : [ chk05 ]
});

var fieldIzquierda6 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 140,
	height : 40,
	items : [ chk06 ]
});

var TxtResumen = new Ext.form.HtmlEditor({
	fieldLabel : 'Resumen de Hechos',
	name : 'TxtResumen',
	width : 750,
	height : 95
});

var TxtAcciones = new Ext.form.HtmlEditor({
	fieldLabel : 'Acciones a Realizar',
	name : 'TxtAcciones',
	width : 750,
	height : 95
});

// /LISTAS
var datasino2 = [ 'NO', 'SI' ];
var cboDerivacion = new Ext.form.ComboBox({
	fieldLabel : 'Derivacion',
	name : 'cboDerivacion',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Seleccione el Derivacion',
	store : datasino2,
	emptyText : 'Seleccione el Derivacion',
	triggerAction : 'all',
	editable : false,
	value : '1',
	width : 50
});

var txtDerivaDe = new Ext.form.TextField({
	id : 'txtDerivaDe',
	name : 'txtDerivaDe',
	style : 'float:left',
	blankText : 'Ingrese el motivo de la Derivacion',
	width : 400
});

var dataestado = [ '0.Inactivo', '1.Activo' ];
var txtestado = new Ext.form.ComboBox({
	fieldLabel : 'Estado',
	name : 'txtestado',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Seleccione el Estado',
	store : dataestado,
	emptyText : 'Seleccione Estado',
	triggerAction : 'all',
	editable : false,
	value : '1',
	width : 250
});

var datasexo = [ 'F', 'M' ];
var combotipocliente = new Ext.form.ComboBox({
	fieldLabel : 'Sexo',
	name : 'combotipocliente',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Sel. Sexo',
	store : datasexo,
	emptyText : 'Sel. Sexo',
	triggerAction : 'all',
	editable : false,
	value : '1.Minorista',
	width : 250
});

var cboSexo = new Ext.form.ComboBox({
	fieldLabel : 'Sexo',
	name : 'cboSexo',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Sel. Sexo',
	store : datasexo,
	emptyText : 'Sel. Sexo',
	triggerAction : 'all',
	editable : false,
	value : 'M',
	width : 250
});

var cboSexo1 = new Ext.form.ComboBox({
	fieldLabel : 'Sexo',
	name : 'cboSexo1',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Sel. Sexo',
	store : datasexo,
	emptyText : 'Sel. Sexo',
	triggerAction : 'all',
	editable : false,
	value : 'M',
	width : 250
});

var cboSexo2 = new Ext.form.ComboBox({
	fieldLabel : 'Sexo',
	name : 'cboSexo2',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Sel. Sexo',
	store : datasexo,
	emptyText : 'Sel. Sexo',
	triggerAction : 'all',
	editable : false,
	value : 'M',
	width : 250
});

var txtLugar = new Ext.form.TextField({
	fieldLabel : 'Lugar',
	name : 'txtLugar',
	allowBlank : false,
	blankText : 'Ingrese direccion.',
	width : 400
});

// PROVINCIAS DE PAISES
var storeciudad = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarciudad.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'idciudad', 'ciudad' ]
});

var combociudad = new Ext.form.ComboBox({
	fieldLabel : 'Ciudad',
	name : 'combociudad',
	forceSelection : true,
	store : storeciudad,
	// hiddenName:'combociudadid',
	emptyText : 'Seleccione Ciudad',
	triggerAction : 'all',
	editable : false,
	displayField : 'ciudad',
	valueField : 'idciudad',
	allowBlank : false,
	blankText : 'Seleecione Ciudad',
	value : 'Ucayali',
	width : 250
});

// PAIS DEL CLIENTE
// url:'formexpedientes.php?cargarpais=true',
var storepais = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarpais.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'idpais', 'pais' ]
});
storepais.load();

var combopais = new Ext.form.ComboBox({
	fieldLabel : 'Pais',
	name : 'combopais',
	forceSelection : true,
	store : storepais,
	emptyText : 'Seleccione Pais',
	triggerAction : 'all',
	editable : false,
	displayField : 'pais',
	valueField : 'idpais',
	allowBlank : false,
	blankText : 'Seleecione el zona',
	value : 'Peru',
	width : 250,
	listeners : {
		'select' : function(cmb, data, idx) {
			combociudad.setValue('');
			storeciudad.reload({
				params : {
					xidpais : combopais.getValue()
				}
			});
		}
	}
});

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
// //////////////////////// CONFIGURACION DEL GRID INFORMANTES
// //////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

// ////////////////COMBOS

var storedepartamentos = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarDepartamentos.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyDepartamento', 'Departamento' ]
});
storedepartamentos.load();

var comboDepartamentos = new Ext.form.ComboBox({
	fieldLabel : 'Departamento',
	name : 'comboDepartamentos',
	forceSelection : true,
	store : storedepartamentos,
	hiddenName : 'comboDepartamentosId',
	emptyText : 'Seleccione Departamento',
	triggerAction : 'all',
	editable : false,
	displayField : 'Departamento',
	valueField : 'KyDepartamento',
	allowBlank : false,
	blankText : 'Seleccione Departamento',
	listeners : {
		'select' : function(cmb, data, idx) {
			comboProvincias.setValue('');
			comboDistritos.setValue('');
			storeprovincias.load({
				params : {
					dpto : comboDepartamentos.getValue()
				}
			});
		}
	}
});

var storeprovincias = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarProvincias.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyProvincia', 'Provincia' ]
});

var comboProvincias = new Ext.form.ComboBox({
	fieldLabel : 'Provincia',
	name : 'comboProvincias',
	forceSelection : true,
	mode : 'local',
	store : storeprovincias,
	hiddenName : 'comboProvinciasId',
	emptyText : 'Seleccione Provincia',
	triggerAction : 'all',
	editable : false,
	displayField : 'Provincia',
	valueField : 'KyProvincia',
	allowBlank : false,
	blankText : 'Seleccione Distrito',
	listeners : {
		'select' : function(cmb, data, idx) {
			comboDistritos.setValue('');
			storedistritos.load({
				params : {
					dpto : comboDepartamentos.getValue(),
					prov : comboProvincias.getValue()
				}
			});
		}
	}
});

var storedistritos = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarDistritos.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyDistrito', 'Distrito' ]
});

var comboDistritos = new Ext.form.ComboBox({
	fieldLabel : 'Distrito',
	name : 'comboDistritos',
	forceSelection : true,
	store : storedistritos,
	mode : 'local',
	hiddenName : 'comboDistritosId',
	emptyText : 'Seleccione Distrito',
	triggerAction : 'all',
	editable : false,
	displayField : 'Distrito',
	valueField : 'KyDistrito',
	allowBlank : false,
	blankText : 'Seleccione Distrito',
	listeners : {
		'select' : function(cmb, data, idx) {
			comboZonas.setValue('');
			storezonas.load({
				params : {
					dpto : comboDepartamentos.getValue(),
					prov : comboProvincias.getValue(),
					dist : comboDistritos.getValue()
				}
			});
		}
	}
});

// //////////////////////////////////////////////////////////STORE ZONAS
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var storezonas = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarZonas.htm', // +sessvars.idempresa,
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'codzona', 'zonas' ]
});

var comboZonas = new Ext.form.ComboBox({
	fieldLabel : 'Zona',
	name : 'comboZonas',
	forceSelection : true,
	store : storezonas,
	mode : 'local',
	hiddenName : 'comboZonasId',
	emptyText : 'Seleccione Zona',
	triggerAction : 'all',
	editable : false,
	displayField : 'zonas',
	valueField : 'codzona',
	allowBlank : false,
	blankText : 'Seleccione Zona'
});

// //////////////////////////////////////////////////////////STORE ZONAS
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var storeNuevoInformante = new Ext.data.JsonStore({
	url : 'formexpedientes/newinformante.htm'
});

var btnNuevoInformante = new Ext.Button({
	id : 'btnNuevoInformante',
	width : 100,
	iconAlign : 'top',
	text : 'Nuevo',
	icon : './icon/nuevo.png',
	minWidth : 80,
	handler : function() {
		storeNuevoInformante.load({
			params : {
				KyExpediente : txtKyExpediente.getValue()
			},
			callback : function() {
				estoreEspecInform.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

var storeEliminarInformante = new Ext.data.JsonStore({
	url : 'formexpedientes/delinformante.htm'
});

var btnEliminarInformante = new Ext.Button({
	id : 'btnEliminarInformante',
	width : 100,
	iconAlign : 'top',
	text : 'Eliminar',
	icon : './icon/eliminar.gif',
	minWidth : 80,
	handler : function() {
		storeEliminarInformante.load({
			params : {
				KyInformante : clavederegistroinformantes
			},
			callback : function() {
				estoreEspecInform.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

// ////////////////FIN COMBOS
var estoreEspecInform = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarinformantes.htm',
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
	fields : [ 'KyInformante', 'Dni', 'Nombre', 'Nombres', 'ApPaterno',
			'ApMaterno', 'Edad', 'Sexo', 'Domicilio', 'Telefono', 'Ocupacion',
			'Relacion_Afectado', 'KyDepartamento', 'EstCivil' ]
});

var pagerInform = new Ext.PagingToolbar({
	store : estoreEspecInform, // <--grid and PagingToolbar using same store
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
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

var grid1Inform = new Ext.grid.EditorGridPanel({
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
	}, {
		header : 'Dni',
		width : 80,
		sortable : true,
		dataIndex : 'Dni',
		editor : {
			xtype : 'textfield',
			maskRe : '[0-9]',
			allowBlank : false,
			autoCreate : {
				tag : 'input',
				type : 'text',
				maxlength : '8',
				autocomplete : 'off'
			}
		}
	}, {
		header : 'Nombre',
		width : 150,
		sortable : true,
		dataIndex : 'Nombre',
		editor : {
			xtype : 'textfield',
			allowBlank : true
		},
		hidden : true
	}, {
		header : 'Nombres',
		width : 150,
		sortable : true,
		dataIndex : 'Nombres',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Apellido Paterno',
		width : 100,
		sortable : true,
		dataIndex : 'ApPaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Apellido Materno',
		width : 100,
		sortable : true,
		dataIndex : 'ApMaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
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
			xtype : 'numberfield',
			allowBlank : false,
			autoCreate : {
				tag : 'input',
				type : 'text',
				maxlength : '2',
				autocomplete : 'off'
			}
		}
	}, {
		header : 'Sexo',
		width : 40,
		sortable : true,
		dataIndex : 'Sexo',
		editor : cboSexo
	}, {
		header : 'Telefono',
		width : 60,
		sortable : true,
		dataIndex : 'Telefono',
		editor : {
			xtype : 'numberfield',
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
	}, {
		header : 'Est.Civil',
		width : 70,
		sortable : true,
		dataIndex : 'EstCivil',
		editor : cboEstadoCivil1
	}, ],
	autoWidth : true,
	height : 250,
	frame : false,
	tbar : [ pagerInform, '->', btnEliminarInformante, btnNuevoInformante ],
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

	},
	editRegistro : function(grid1Inform, index, event) {
		var record = grid1Inform.getStore().getAt(index);
		clavederegistroinformantes = record.get('KyInformante');
		TxtKyActualizaUbigeo.setValue(record.get('KyInformante'));
		TxtTabla.setValue('Informantes');
		AbrirUbigeo();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID AFECTADOS
// //////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var storeNuevoAfectado = new Ext.data.JsonStore({
	url : 'formexpedientes/newafectado.htm'
});

var btnNuevoAfectado = new Ext.Button({
	id : 'btnNuevoAfectado',
	width : 100,
	iconAlign : 'top',
	text : 'Nuevo',
	icon : './icon/nuevo.png',
	minWidth : 80,
	handler : function() {
		storeNuevoAfectado.load({
			params : {
				KyExpediente : txtKyExpediente.getValue()
			},
			callback : function() {
				estoreEspecAfect.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

var storeEliminarAfectado = new Ext.data.JsonStore({
	url : 'formexpedientes/delafectado.htm'
});

var btnEliminarAfectado = new Ext.Button({
	id : 'btnEliminarAfectado',
	width : 100,
	iconAlign : 'top',
	text : 'Eliminar',
	icon : './icon/eliminar.gif',
	minWidth : 80,
	handler : function() {
		storeEliminarAfectado.load({
			params : {
				KyAfectado : clavederegistroafectados
			},
			callback : function() {
				estoreEspecAfect.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});
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
			'ApMaterno', 'Domicilio', 'Edad', 'Sexo', 'KyDepartamento',
			'Discapacidad', 'MadreSoltera', 'EstCivil' ]
});

var pagerAfect = new Ext.PagingToolbar({
	store : estoreEspecAfect, // <--grid and PagingToolbar using same store
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
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

var dataSINO = [ 'NO', 'SI' ];
var cboSINO = new Ext.form.ComboBox({
	name : 'cboSINO',
	forceSelection : true,
	allowBlank : false,
	store : dataSINO,
	triggerAction : 'all',
	editable : false,
	value : 'NO'
});
// url:'formexpedientes.php?cargarDiscapacidad=true&idempresa=1',
// //+sessvars.idempresa,
var storediscapacidad = new Ext.data.JsonStore({
	url : 'formexpedientes/cargarDiscapacidad.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyDiscapacidad', 'Discapacidad' ]
});
storediscapacidad.load();

var comboDiscapacidad = new Ext.form.ComboBox({
	fieldLabel : 'Distrito',
	name : 'comboDiscapacidad',
	forceSelection : true,
	store : storediscapacidad,
	mode : 'local',
	hiddenName : 'comboDiscapacidadId',
	emptyText : 'Seleccione Discapacidad',
	triggerAction : 'all',
	editable : false,
	displayField : 'Discapacidad',
	valueField : 'Discapacidad',
	allowBlank : false,
	blankText : 'Seleccione Discapacidad'
});

var grid1Afect = new Ext.grid.EditorGridPanel({
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
			xtype : 'numberfield',
			allowBlank : false,
			autoCreate : {
				tag : 'input',
				type : 'text',
				maxlength : '8',
				autocomplete : 'off'
			}
		}
	}, {
		header : 'Nombre',
		width : 120,
		sortable : true,
		dataIndex : 'Nombre',
		editor : {
			xtype : 'textfield',
			allowBlank : true
		},
		hidden : true
	}, {
		header : 'Nombres',
		width : 150,
		sortable : true,
		dataIndex : 'Nombres',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Apellido Paterno',
		width : 100,
		sortable : true,
		dataIndex : 'ApPaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Apellido Materno',
		width : 100,
		sortable : true,
		dataIndex : 'ApMaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Domicilio',
		width : 140,
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
			xtype : 'numberfield',
			allowBlank : false,
			autoCreate : {
				tag : 'input',
				type : 'text',
				maxlength : '2',
				autocomplete : 'off'
			}
		}
	}, {
		header : 'Sexo',
		width : 40,
		sortable : true,
		dataIndex : 'Sexo',
		editor : cboSexo1
	}, {
		header : 'Discapacitado',
		width : 70,
		sortable : true,
		dataIndex : 'Discapacidad',
		editor : comboDiscapacidad
	}, {
		header : 'Madre Gestante',
		width : 70,
		sortable : true,
		dataIndex : 'MadreSoltera',
		editor : cboSINO
	}, {
		header : 'Est.Civil',
		width : 70,
		sortable : true,
		dataIndex : 'EstCivil',
		editor : cboEstadoCivil
	}, ],
	autoWidth : true,
	height : 250,
	frame : false,
	tbar : [ pagerAfect, '->', btnEliminarAfectado, btnNuevoAfectado ],
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
	},
	editRegistro : function(grid1Inform, index, event) {
		var record = grid1Afect.getStore().getAt(index);
		clavederegistroafectados = record.get('KyAfectado');
		TxtKyActualizaUbigeo.setValue(record.get('KyAfectado'));
		TxtTabla.setValue('Afectados');
		AbrirUbigeo();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID TRANSGRESORES
// //////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var storeNuevoTransgresor = new Ext.data.JsonStore({
	url : 'formexpedientes/newtransgresor.htm'
});

var btnNuevoTransgresor = new Ext.Button({
	id : 'btnNuevoTransgresor',
	width : 100,
	iconAlign : 'top',
	text : 'Nuevo',
	icon : './icon/nuevo.png',
	minWidth : 80,
	handler : function() {
		storeNuevoTransgresor.load({
			params : {
				KyExpediente : txtKyExpediente.getValue()
			},
			callback : function() {
				estoreEspecTrans.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});

var storeEliminarTransgresor = new Ext.data.JsonStore({
	url : 'formexpedientes/deltransgresor.htm'
});

var btnEliminarTransgresor = new Ext.Button({
	id : 'btnEliminarTransgresor',
	width : 100,
	iconAlign : 'top',
	text : 'Eliminar',
	icon : './icon/eliminar.gif',
	minWidth : 80,
	handler : function() {
		storeEliminarTransgresor.load({
			params : {
				KyTransgresor : clavederegistrotransgresor
			},
			callback : function() {
				estoreEspecTrans.load({
					params : {
						idexpediente : txtKyExpediente.getValue()
					}
				});
			}
		});
	}
});
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
			'KyDepartamento', 'KyProvincia', 'Ocupacion', 'Relacion',
			'EstCivil' ]
});

var pagerTrans = new Ext.PagingToolbar({
	store : estoreEspecTrans, // <--grid and PagingToolbar using same store
	displayInfo : true,
	displayMsg : 'Registro {0} al {1} de un total de {2}',
	emptyMsg : 'No hay registros que mostrar.',
	pageSize : 15
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

var dataRELACION = [ 'Padre', 'Madre', 'Padrastro', 'Madrastra', 'Hermano(a)',
		'Tio(a)', 'Primo(a)', 'Otro' ];
var cboRELACION = new Ext.form.ComboBox({
	name : 'cboRELACION',
	forceSelection : true,
	allowBlank : false,
	store : dataRELACION,
	triggerAction : 'all',
	editable : false,
	value : 'Seleccione...'
});

var grid1Trans = new Ext.grid.EditorGridPanel({
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
			xtype : 'numberfield',
			allowBlank : false,
			autoCreate : {
				tag : 'input',
				type : 'text',
				maxlength : '8',
				autocomplete : 'off'
			}
		}
	}, {
		header : 'Nombre',
		width : 120,
		sortable : true,
		dataIndex : 'Nombre',
		editor : {
			xtype : 'textfield',
			allowBlank : true
		},
		hidden : true
	}, {
		header : 'Nombres',
		width : 150,
		sortable : true,
		dataIndex : 'Nombres',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Apellido Paterno',
		width : 100,
		sortable : true,
		dataIndex : 'ApPaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Apellido Materno',
		width : 100,
		sortable : true,
		dataIndex : 'ApMaterno',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Domicilio',
		width : 130,
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
			xtype : 'numberfield',
			allowBlank : false,
			autoCreate : {
				tag : 'input',
				type : 'text',
				maxlength : '2',
				autocomplete : 'off'
			}
		}
	}, {
		header : 'Sexo',
		width : 40,
		sortable : true,
		dataIndex : 'Sexo',
		editor : cboSexo2
	}, {
		header : 'Ocupacion',
		width : 70,
		sortable : true,
		dataIndex : 'Ocupacion',
		editor : {
			xtype : 'textfield',
			allowBlank : false
		}
	}, {
		header : 'Relacion Afectado',
		width : 70,
		sortable : true,
		dataIndex : 'Relacion',
		editor : cboRELACION
	}, {
		header : 'Est.Civil',
		width : 70,
		sortable : true,
		dataIndex : 'EstCivil',
		editor : cboEstadoCivil2
	}, ],
	autoWidth : true,
	height : 250,
	frame : false,
	tbar : [ pagerTrans, '->', btnEliminarTransgresor, btnNuevoTransgresor ],
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

	},
	editRegistro : function(grid1Trans, index, event) {
		var record = grid1Trans.getStore().getAt(index);
		clavederegistrotransgresor = record.get('KyTransgresor');
		TxtKyActualizaUbigeo.setValue(record.get('KyTransgresor'));
		TxtTabla.setValue('Transgresores');
		AbrirUbigeo();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// CONFIGURACION DEL GRID PRINCIPAL
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////
// url:'formexpedientes/cargar.htm?demuna='+txtKyDemunaIni.getValue(),
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
			'Derivacion', 'DerivaDe', 'c1', 'c2', 'c3', 'c4', 'c5', 'c6' ]
});
estoreEspec.load();

var btnBuscarYaEspec = new Ext.Button({
	id : 'btnBuscar',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Buscar',
	icon : './icon/buscar.png',
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

function formatosexo(val) {
	if (val == 0) {
		return '<span style="color:#000;font-size:11;">M</span>';
	}
	if (val == 1) {
		return '<span style="color:#000;font-size:11;">F</span>';
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
	name : 'Derivacion'
}, {
	name : 'DerivaDe'
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
		header : 'DerivaDe',
		width : 180,
		sortable : true,
		renderer : ftogris,
		dataIndex : 'DerivaDe',
		hidden : true
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
	height : 380,
	frame : false,
	bbar : [ pager ],
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
		txtRegActualEspec.setValue(record.get('KyExpediente'));
		txtKyExpediente.setValue(record.get('KyExpediente'));
		txtdependencia.setValue(record.get('KyDependencia'));
		TxtNroExpediente.setValue(record.get('NroExpediente'));
		txtLugar.setValue(record.get('Lugar'));
		txtFecha.setValue(record.get('Fecha'));
		TxtResumen.setValue(record.get('ResumenHechos'));
		TxtAcciones.setValue(record.get('Acciones'));
		cboDerivacion.setValue(record.get('Derivacion'));
		txtDerivaDe.setValue(record.get('DerivaDe'));

		var estexp = "";
		switch (record.get('EstExp')) {
		case "0":
			estexp = "0.Inactivo";
			break;
		case "1":
			estexp = "1.Activo";
			break;
		}
		txtestado.setValue(estexp);

		chk01.setValue(record.get('c1'));
		chk02.setValue(record.get('c2'));
		chk03.setValue(record.get('c3'));
		chk04.setValue(record.get('c4'));
		chk05.setValue(record.get('c5'));
		chk06.setValue(record.get('c6'));

		clavederegistro = record.get('KyExpediente');

		// PROPIEDAD FACTURABLE
		var sexo = "";
		switch (record.get('AfSexo')) {
		case "0":
			sexo = "M";
			break;
		case "1":
			sexo = "F";
			break;
		}
		combotipocliente.setValue(sexo);

		// habilitar botones
		btnEditarEspec.enable();
		btnEliminarEspec.enable();
		btnImagen.enable();
		storeciudad.reload({
			params : {
				xidpais : record.get('ypais')
			}
		});
	},
	editRegistro : function(grid1, index, event) {
		AbrirNuevoExpediente();
	}
};
Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// BOTONES Y TEXTOS PAR PRINFORM
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var txtRegActualEspec = new Ext.form.TextField({
	name : 'txtRegActualEspec',
	id : 'txtRegActualEspec',
	style : 'text-align: center',
	readOnly : true,
	width : 50,
	value : '1',
	hidden : true
});

var btnNuevoEspec = new Ext.Button({
	id : 'btnNuevoEspec',
	x : 20,
	y : 470,
	width : 100,
	iconAlign : 'top',
	text : 'Nuevo',
	icon : './icon/nuevo.png',
	minWidth : 80,
	handler : function() {

		var storeNuevoExpediente = new Ext.data.JsonStore({
			url : 'formexpedientes/nuevoexpediente.htm'
		});

		storeNuevoExpediente.load({
			params : {
				KyDependencia : txtKyDemunaIni.getValue()
			},
			callback : function() {
				estoreEspec.load();
			}
		});
	}
});

var btnCerrar = new Ext.Button({
	id : 'btnCerrar',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Cerrar',
	icon : './icon/cerrar.png',
	handler : function() {
		winExpediente.hide();
	}
});

var btnEliminarEspec = new Ext.Button({
	id : 'btnEliminarEspec',
	x : 130,
	y : 470,
	width : 100,
	iconAlign : 'top',
	text : 'Eliminar',
	icon : './icon/eliminar.gif',
	minWidth : 80,
	disabled : true,
	handler : function() {
		btnEditarEspec.setDisabled(true);
		btnEliminarEspec.setDisabled(true);
		Ext.MessageBox.confirm('Cuidado!',
				'Esta seguro de eliminar el registro seleccionado? Clave de Registro : '
						+ clavederegistro, function(btn) {
					if (btn == 'yes') {
						FrmExpediente.validarAccesoEspec();
					}
				});
	}
});

var btnEditarEspec = new Ext.Button({
	id : 'btnEditarEspec',
	x : 240,
	y : 470,
	width : 100,
	iconAlign : 'top',
	text : 'Editar',
	icon : './icon/editar.png',
	minWidth : 80,
	disabled : true,
	handler : function() {
		paneltabs.setActiveTab(0);
		AbrirNuevoExpediente();
	}
});

function filterEspec(xcampo, xvalor) {
	this.estoreEspec.filterBy(function(record, id) {
		return record.get(xcampo) == xvalor;
	});
};

var btnImagen = new Ext.Button({
	id : 'btnImagen',
	x : 240,
	y : 470,
	width : 50,
	iconAlign : 'top',
	scale : 'large',
	disabled : true,
	// text : 'Imagen',
	icon : './icon/jpeg.png',
	minWidth : 80,
	handler : function() {

		text02.setValue(txtrazonsocial.getValue());
		txtId2.setValue(txtidcliente.getValue());
		txtId0.setValue(txtidempresa.getValue());
		cargarImagen();
	}
});

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
			tbar : [ lblFiltrar, comboFiltroEspec, lblFiltrara,
					txtFiltrarEspec, btnBuscarYaEspec, '->', btnPrintExpediente ],
			items : [ grid1 ], // btnImagen
			bbar : [ '->', btnEliminarEspec, btnEditarEspec, btnNuevoEspec,
					txtRegActualEspec ],
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
			title : '1. Recepcion de Expedientes ',
			resizable : false,
			closeAction : 'hide',
			closable : false,
			draggable : true,
			plain : false,
			border : false,
			modal : false,
			items : [ FrmExpediente ],
			listeners : {
				show : function() {
					AbrirNuevoExpediente();
					winNuevoExpediente.hide();
				}
			}
		});
	}
	winExpediente.show();
};

var fieldIzquierda7 = new Ext.form.FieldSet({
	style : 'float:left;',
	items : [ TxtResumen, TxtAcciones ]
});

var paneltabs = new Ext.TabPanel({
	name : 'paneltabs',
	activeTab : 0,
	border : false,
	enableTabScroll : true,
	defaults : {
		height : 500,
		bodyStyle : 'padding:10px;background-color:#fff'
	},
	items : [
			{
				title : 'Datos del Informante',
				layout : 'form',
				items : [ grid1Inform ]
			},
			{
				title : 'Datos del Afectado/Beneficiario',
				layout : 'form',
				items : [ grid1Afect ]
			},
			{
				title : 'Datos del Transgresor/Obligado',
				layout : 'form',
				labelAlign : 'top',
				items : [ grid1Trans ]
			},
			{
				title : 'Resumen y Acciones',
				layout : 'form',
				items : [ fieldIzquierda1, fieldIzquierda2, fieldIzquierda3,
						fieldIzquierda4, fieldIzquierda5, fieldIzquierda6,
						fieldIzquierda7 ]
			}, {
				title : 'Materias del Caso',
				layout : 'form',
				labelAlign : 'top',
				items : [ grid1Mater ]
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
			// //////////////////////////////////
			// ///////////////////////////////////
			items : [ txtdependencia, txtKyExpediente, TxtNroExpediente,
					txtFecha, txtLugar, cboDerivacion, txtDerivaDe, txtestado,
					paneltabs ],
			// ////////////////////////////////////
			// //////////////////////////////////////
			validarAccesoEspec : function() {
				if (this.getForm().isValid()) {
					this
							.getForm()
							.submit(
									{
										url : 'formexpedientes/nuevo.htm',// sessvars.idempresa,
										method : 'POST',
										waitTitle : 'Conectando',
										waitMsg : 'Intentando guardar el registro...',
										success : function(form, action) {
											// Ext.Msg.alert('Conexion Exitosa',
											// action.result.msg,function(){
											// winNuevoExpediente.hide();
											estoreEspec.reload();
											btnEditarEspec.setDisabled(true);
											btnEliminarEspec.setDisabled(true);

											// });
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

var btnGuardarEspec = new Ext.Button({
	id : 'btnGuardarEspec',
	x : 240,
	y : 470,
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Guardar',
	icon : './icon/disk.png',
	handler : function() {

		// DETALLE DE GRID INFORMANTES
		var nItem;
		grid1Inform.store.each(function(record, index) {
			nItem = record.get('KyInformante');
			KyDpto = record.get('KyDepartamento');
			Nombres = record.get('Nombres');
			ApPaterno = record.get('ApPaterno');
			ApMaterno = record.get('ApMaterno');
			Domicilio = record.get('Domicilio');
			Dni = record.get('Dni');
			Edad = record.get('Edad');
			Sexo = record.get('Sexo');
			Telefono = record.get('Telefono');
			Ocupacion = record.get('Ocupacion');
			Relacion = record.get('Relacion_Afectado');
			EstCivil = record.get('EstCivil');
			var storeGrabarInformante = new Ext.data.JsonStore({
				url : 'formexpedientes/informantes.htm?nItemInformante='
						+ nItem
			});
			storeGrabarInformante.load({
				params : {
					KyDpto : KyDpto,
					Nombres : Nombres,
					ApPaterno : ApPaterno,
					ApMaterno : ApMaterno,
					Domicilio : Domicilio,
					Dni : Dni,
					Edad : Edad,
					Sexo : Sexo,
					Telefono : Telefono,
					Ocupacion : Ocupacion,
					Relacion : Relacion,
					EstCivil : EstCivil
				},
				callback : function() {
					estoreEspecInform.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}
			});
		});

		// DETALLE DE GRID AFECTADOS

		var nItem;
		grid1Afect.store.each(function(record, index) {
			nItem = record.get('KyAfectado');
			KyDpto = record.get('KyDepartamento');
			Nombres = record.get('Nombres');
			ApPaterno = record.get('ApPaterno');
			ApMaterno = record.get('ApMaterno');
			Domicilio = record.get('Domicilio');
			Dni = record.get('Dni');
			Edad = record.get('Edad');
			Sexo = record.get('Sexo');
			Discapacidad = record.get('Discapacidad');
			Madre = record.get('MadreSoltera');
			EstCivil = record.get('EstCivil');
			var storeGrabarAfectado = new Ext.data.JsonStore({
				url : 'formexpedientes/afectados.htm?nItemAfectado=' + nItem
			});
			storeGrabarAfectado.load({
				params : {
					KyDpto : KyDpto,
					Nombres : Nombres,
					ApPaterno : ApPaterno,
					ApMaterno : ApMaterno,
					Domicilio : Domicilio,
					Dni : Dni,
					Edad : Edad,
					Sexo : Sexo,
					Discapacidad : Discapacidad,
					Madre : Madre,
					EstCivil : EstCivil
				},
				callback : function() {
					estoreEspecAfect.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}
			});
		});

		// DETALLE DE GRID TRANSGRESORES

		var nItem;
		grid1Trans.store.each(function(record, index) {
			nItem = record.get('KyTransgresor');
			KyDpto = record.get('KyDepartamento');
			Nombres = record.get('Nombres');
			ApPaterno = record.get('ApPaterno');
			ApMaterno = record.get('ApMaterno');
			Domicilio = record.get('Domicilio');
			Dni = record.get('Dni');
			Edad = record.get('Edad');
			Sexo = record.get('Sexo');
			Ocupacion = record.get('Ocupacion');
			Relacion = record.get('Relacion');
			EstCivil = record.get('EstCivil');
			var storeGrabarTransgresor = new Ext.data.JsonStore({
				url : 'formexpedientes/trangresor.htm?nItemTransgresor='
						+ nItem
			});
			storeGrabarTransgresor.load({
				params : {
					KyDpto : KyDpto,
					Nombres : Nombres,
					ApPaterno : ApPaterno,
					ApMaterno : ApMaterno,
					Domicilio : Domicilio,
					Dni : Dni,
					Edad : Edad,
					Sexo : Sexo,
					Ocupacion : Ocupacion,
					Relacion : Relacion,
					EstCivil : EstCivil
				},
				callback : function() {
					estoreEspecTrans.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}
			});
		});
		FrmnuevoExpediente.validarAccesoEspec();
	}
});

var btnCerrarNuevo = new Ext.Button({
	id : 'btnCerrarNuevo',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Cerrar',
	icon : './icon/cerrar.png',
	handler : function() {
		winNuevoExpediente.hide();
	}
});

function AbrirNuevoExpediente() {
	if (!winNuevoExpediente) {
		winNuevoExpediente = new Ext.Window({
			layout : 'fit',
			title : 'Nuevo/Editar Expediente',
			width : 950,
			height : 630,
			resizable : false,
			closeAction : 'hide',
			closable : true,
			draggable : true,
			plain : true,
			border : false,
			modal : true,
			style : 'margin:10px;',
			items : [ FrmnuevoExpediente ],
			tbar : [ '->', btnCerrarNuevo ],
			bbar : [ '->', btnGuardarEspec ],
			listeners : {
				show : function() {
					btnEditarEspec.setDisabled(true);
					btnEliminarEspec.setDisabled(true);

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
					estoreMaterias.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});

				}

			}
		});
	}
	winNuevoExpediente.show();
}

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// //////////////////////////////////////// U U BBBB IIIIII GGGGGGGG
// /////////////////////////////////////////////////////
// //////////////////////////////////////// U U B B II GG
// /////////////////////////////////////////////////////
// //////////////////////////////////////// U U BBBB II GG GGGG
// /////////////////////////////////////////////////////
// //////////////////////////////////////// U U B B II GG G G
// /////////////////////////////////////////////////////
// //////////////////////////////////////// UUUUU BBBB IIIIII GGGGGGGG
// /////////////////////////////////////////////////////
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var TxtTabla = new Ext.form.TextField({
	name : 'TxtTabla',
	id : 'TxtTabla',
	style : 'text-align: center',
	readOnly : true,
	width : 200,
	hidden : false
});

var TxtKyActualizaUbigeo = new Ext.form.TextField({
	name : 'TxtKyActualizaUbigeo',
	id : 'TxtKyActualizaUbigeo',
	style : 'text-align: center',
	readOnly : true,
	width : 50,
	hidden : false
});

var storeUbigeoInformante = new Ext.data.JsonStore({
	url : 'formexpedientes/setUbigeo.htm'
});

var btnGrabarUbigeo = new Ext.Button({
	id : 'btnGrabarUbigeo',
	width : 100,
	scale : 'large',
	iconAlign : 'top',
	icon : './icon/ok111.png',
	minWidth : 80,
	handler : function() {

		var KyDpto = comboDepartamentos.getValue() + comboProvincias.getValue()
				+ comboDistritos.getValue() + comboZonas.getValue();
		storeUbigeoInformante.load({
			params : {
				KyTabla : TxtKyActualizaUbigeo.getValue(),
				KyDpto : KyDpto,
				Tabla : TxtTabla.getValue()
			},
			callback : function() {
				var tabla = TxtTabla.getValue();
				if (tabla == 'Informantes') {
					estoreEspecInform.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}
				if (tabla == 'Afectados') {
					estoreEspecAfect.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}
				if (tabla == 'Transgresores') {
					estoreEspecTrans.load({
						params : {
							idexpediente : txtKyExpediente.getValue()
						}
					});
				}
			}
		});
		winUbigeo.hide();
	}
});

function AbrirUbigeo() {
	if (!winUbigeo) {
		winUbigeo = new Ext.Window({
			layout : 'form',
			bodyStyle : 'padding: 10px;background-color:#FFFFFF;',
			title : 'Ubigeo',
			width : 300,
			height : 250,
			resizable : false,
			closeAction : 'hide',
			closable : true,
			draggable : true,
			plain : false,
			border : false,
			modal : false,
			style : 'margin:10px;',
			items : [ comboDepartamentos, comboProvincias, comboDistritos,
					comboZonas, TxtKyActualizaUbigeo, TxtTabla ],
			bbar : [ '->', btnGrabarUbigeo ],
			listeners : {
				show : function() {
				}
			}
		});
	}
	winUbigeo.show();
}