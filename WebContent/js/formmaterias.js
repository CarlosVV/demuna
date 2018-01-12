if (txtKyUsuario.getValue() != 1) {

	var FrmUsuarios = new Ext.Panel(
			{
				frame : true,
				layout : 'form',
				renderTo : 'DivformExpediente',
				html : '<center><h1>Lo Sentimos Ud. no tiene acceso a este modulo.<br/>Crear Materias No esta Disponible.</h1></center> ',
				height : 500
			});

	Ext.MessageBox.show({
		title : 'Niveles de Acceso',
		msg : 'Lo Sentimos. Usted no tiene acceso a este modulo.',
		buttons : Ext.MessageBox.OK,
		icon : Ext.MessageBox.ERROR
	});

}
if (txtKyNivelUsuario.getValue() == 1) {

	var winNuevamateria, winUbigeo, winCargarImagen, claveregistro;

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////
	// ///////////////////////////////////////////////////
	var storedepartamentos = new Ext.data.JsonStore({
		url : 'formexpedientes/cargarDepartamentos.htm', // +sessvars.idempresa,
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
		url : 'formexpedientes/cargarProvincias.htm?dpto='
				+ comboDepartamentos.getValue(),
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
		blankText : 'Seleccione Distrito'
	});

	var btnGrabarUbigeo = new Ext.Button({
		id : 'btnGrabarUbigeo',
		width : 100,
		scale : 'large',
		iconAlign : 'top',
		icon : 'icon/ok111.png',
		minWidth : 80,
		handler : function() {
			winUbigeo.hide();
			var KyDpto = comboDepartamentos.getValue()
					+ comboProvincias.getValue() + comboDistritos.getValue();
			txtUbicacion.setValue(KyDpto);
		}
	});

	function AbrirUbigeo() {
		if (!winUbigeo) {
			winUbigeo = new Ext.Window(
					{
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
						items : [ comboDepartamentos, comboProvincias,
								comboDistritos ],
						bbar : [ '->', btnGrabarUbigeo ],
						listeners : {
							show : function() {
							}
						}
					});
		}
		winUbigeo.show();
	}

	// ///////////////////////////////////////////////////
	// ///////////////////////////////////////////////////

	var btnNuevomateria = new Ext.Button({
		id : 'btnNuevomateria',
		x : 20,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Nuevo',
		icon : 'icon/nuevo.png',
		minWidth : 80,
		handler : function() {
			var storeNuevamateria = new Ext.data.JsonStore({
				url : 'formmaterias/nuevamateria.htm'
			});
			storeNuevamateria.load({
				callback : function() {
					storematerias.load();
				}
			});
		}
	});

	var btnEliminarmateria = new Ext.Button({
		id : 'btnEliminarmateria',
		x : 130,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Eliminar',
		icon : 'icon/eliminar.gif',
		minWidth : 80,
		disabled : true,
		handler : function() {
			btnEditarmateria.setDisabled(true);
			btnEliminarmateria.setDisabled(true);
			Ext.MessageBox.confirm('Cuidado!',
					'Esta seguro de eliminar la materia Seleccionada?'
							+ claveregistro, function(btn) {
						if (btn == 'yes') {
							var storeEliminamateria = new Ext.data.JsonStore({
								url : 'formmaterias/eliminamateria.htm'
							});
							storeEliminamateria.load({
								params : {
									KyMateria : claveregistro
								},
								callback : function() {
									storematerias.load();
								}
							});
						}
					});
		}
	});

	var btnEditarmateria = new Ext.Button({
		id : 'btnEditarmateria',
		x : 240,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Editar',
		icon : 'icon/editar.png',
		minWidth : 80,
		disabled : true,
		handler : function() {
			AbrirNuevamateria();
		}
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// LISTA DE Materias //////////////////////////
	// //////////////////////// ///////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////

	function ftoprincipal(val) {
		return '<span style="color:#3366CC; font-weight:bold;">' + val
				+ '</span>';
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
			return '<span style="color:#000;font-size:11;"> ' + val
					+ ' : Activo </span>';
		}
	}

	function formatoSINO(val) {
		if (val == "SI") {
			return '<span style="color:#000;font-size:11;">SI</span>';
		}
		if (val == "NO") {
			return '<span style="color:#FF0000;font-size:11;">NO</span>';
		}
	}

	/* formmaterias.php?cargar=true&idempresa=1 */
	var storematerias = new Ext.data.JsonStore({
		url : 'formmaterias/cargar.htm',
		root : 'data',
		totalProperty : 'total',
		paramNames : {
			start : 'offset',
			limit : 'size',
			sort : 'sort',
			dir : 'dir'
		},
		fields : [ 'KyMateria', 'Materia', 'Conciliable', 'Estado' ]
	});
	storematerias.load();

	var pagermaterias = new Ext.PagingToolbar({
		store : storematerias, // <--grid and PagingToolbar using same store
		displayInfo : true,
		displayMsg : 'Registro {0} al {1} de un total de {2}',
		emptyMsg : 'No hay registros que mostrar.',
		pageSize : 15
	});

	var myReadermaterias = new Ext.data.ArrayReader({}, [ {
		name : 'KyMateria'
	}, {
		name : 'Materia'
	}, {
		name : 'Estado'
	} ]);

	var grid1materias = new Ext.grid.GridPanel({
		title : 'Materias',
		frame : false,
		store : storematerias,
		reader : myReadermaterias,
		clicksToEdit : 1,
		columns : [ new Ext.grid.RowNumberer(), {
			header : 'KyMateria',
			width : 50,
			sortable : true,
			dataIndex : 'KyMateria',
			hidden : true
		}, {
			header : 'Materias',
			width : 200,
			sortable : true,
			dataIndex : 'Materia'
		}, {
			header : 'Conciliable?',
			width : 100,
			sortable : true,
			renderer : formatoSINO,
			dataIndex : 'Conciliable'
		}, {
			header : 'Estado',
			width : 50,
			sortable : true,
			renderer : formatestado,
			dataIndex : 'Estado'
		} ],
		viewConfig : {
			forceFit : true
		},
		autoWidth : true,
		height : 450,
		frame : false,
		bbar : [ pagermaterias, '->' ],
		border : false,
		stripeRows : true,
		loadMask : true
	});

	// *********FUNCION LEER DOBLE CLICK**********
	Ext.ns('clickgrid');
	clickgrid.gridclik = {
		init : function() {
			grid1materias.on('rowdblclick', this.editRegistro);
			grid1materias.on('rowclick', this.editMovie);
		},
		editMovie : function(grid1materias, index, event) {
			var record = grid1materias.getStore().getAt(index);
			claveregistro = record.get('KyMateria');
			txtKyMateria.setValue(record.get('KyMateria'));
			;
			txtMateria.setValue(record.get('Materia'));
			cboSINO.setValue(record.get('Conciliable'));
			if (record.get('Estado') == 0) {
				txtEstado.setValue("0.Inactivo");
			}
			if (record.get('Estado') == 1) {
				txtEstado.setValue("1.Activo");
			}

			btnEditarmateria.enable();
			btnEliminarmateria.enable();
		},
		editRegistro : function(grid1materias, index, event) {
			var record = grid1materias.getStore().getAt(index);
			claveregistro = record.get('KyMateria');
			btnEditarmateria.enable();
			btnEliminarmateria.enable();
			AbrirNuevamateria();
		}
	};
	Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

	var Frmmaterias = new Ext.FormPanel({
		frame : true,
		layout : 'form',
		buttonAlign : 'right',
		renderTo : 'DivformExpediente',
		height : 500,
		bbar : [ '->', btnEliminarmateria, btnEditarmateria, btnNuevomateria ],
		items : [ grid1materias ]
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// TXT PARA NUEVAS Materias
	// //////////////////////////
	// //////////////////////// ///////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////

	var btnGuardarmateria = new Ext.Button({
		id : 'btnGuardarmateria',
		width : 80,
		heigth : 30,
		iconAlign : 'top',
		text : 'Guardar',
		icon : 'icon/disk.png',
		handler : function() {

			FrmNuevamateria.validarAccesoEspec();

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
			winNuevamateria.hide();
		}
	});

	var txtKyMateria = new Ext.form.TextField({
		fieldLabel : 'KyMateria',
		name : 'txtKyMateria',
		id : 'txtKyMateria',
		readOnly : true,
		width : 80
	});

	var txtMateria = new Ext.form.TextField({
		fieldLabel : 'Nro.Reg.materia',
		name : 'txtMateria',
		id : 'txtMateria',
		width : 350
	});

	var dataestado = [ '0.Inactivo', '1.Activo' ];
	var txtEstado = new Ext.form.ComboBox({
		fieldLabel : 'Estado',
		name : 'txtEstado',
		forceSelection : true,
		allowBlank : false,
		blankText : 'Seleecione el Estado',
		store : dataestado,
		emptyText : 'Seleccione Estado',
		triggerAction : 'all',
		editable : false,
		value : '1',
		width : 100
	});

	var dataSINO = [ 'SI', 'NO' ];
	var cboSINO = new Ext.form.ComboBox({
		fieldLabel : 'Conciliable?',
		name : 'cboSINO',
		forceSelection : true,
		allowBlank : false,
		blankText : 'Seleecione...',
		store : dataSINO,
		emptyText : 'Seleccione...',
		triggerAction : 'all',
		editable : false,
		value : '1',
		width : 100
	});

	var FrmNuevamateria = new Ext.FormPanel(
			{
				frame : false,
				layout : 'form',
				bodyStyle : 'padding: 10px;',
				buttonAlign : 'right',
				items : [ txtKyMateria, txtMateria, cboSINO, txtEstado ],
				validarAccesoEspec : function() {
					if (this.getForm().isValid()) {
						this
								.getForm()
								.submit(
										{
											url : 'formmaterias/editarmateria.htm?idempresa=1',// sessvars.idempresa,
											method : 'POST',
											waitTitle : 'Conectando',
											waitMsg : 'Intentando guardar el registro...',
											success : function(form, action) {
												winNuevamateria.hide();
												storematerias.load();
												btnEditarmateria
														.setDisabled(true);
												btnEliminarmateria
														.setDisabled(true);
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
														FrmNuevamateria
																.getForm()
																.reset();
													}
												}

											}
										});
					}
				}

			});

	function AbrirNuevamateria() {
		if (!winNuevamateria) {
			winNuevamateria = new Ext.Window({
				layout : 'fit',
				title : 'Nuevo/Editar Materia',
				width : 600,
				height : 250,
				resizable : false,
				closeAction : 'hide',
				closable : true,
				draggable : true,
				plain : true,
				border : false,
				modal : true,
				style : 'margin:10px;',
				items : [ FrmNuevamateria ],
				tbar : [ '->', btnCerrar ],
				bbar : [ '->', btnGuardarmateria ],
				listeners : {
					show : function() {
						/*
						 * btnEditarEspec.setDisabled(true);
						 * btnEliminarEspec.setDisabled(true);
						 * 
						 * estoreEspecInform.load({params:{idexpediente:
						 * txtKyExpediente.getValue()}});
						 * estoreEspecAfect.load({params:{idexpediente:
						 * txtKyExpediente.getValue()}});
						 * estoreEspecTrans.load({params:{idexpediente:
						 * txtKyExpediente.getValue()}});
						 */
					}

				}
			});
		}
		winNuevamateria.show();
	}

}