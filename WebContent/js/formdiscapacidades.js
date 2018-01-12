if (txtKyUsuario.getValue() != 1) {

	var FrmUsuarios = new Ext.Panel(
			{
				frame : true,
				layout : 'form',
				renderTo : 'DivformExpediente',
				html : '<center><h1>Lo Sentimos Ud. no tiene acceso a este modulo.<br/>Crear Discapacidad No esta Disponible.</h1></center> ',
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

	var winNuevadiscapacidad, winUbigeo, winCargarImagen, claveregistro;

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

	// url:'formexpedientes.php?cargarProvincias=true&dpto='+comboDepartamentos.getValue()+"&idempresa=1",
	// //+sessvars.idempresa,
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

	// url:'formexpedientes.php?cargarDistritos=true&idempresa=1',
	// //+sessvars.idempresa,
	var storedistritos = new Ext.data.JsonStore({
		url : 'formexpedientes/cargarDistritos.htm', // +sessvars.idempresa,
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

	var btnNuevodiscapacidad = new Ext.Button({
		id : 'btnNuevodiscapacidad',
		x : 20,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Nuevo',
		icon : 'icon/nuevo.png',
		minWidth : 80,
		handler : function() {

			var storeNuevadiscapacidad = new Ext.data.JsonStore({
				url : 'formdiscapacidades/nuevadiscapacidad.htm'
			});
			storeNuevadiscapacidad.load({
				callback : function() {
					storediscapacidades.load();
				}
			});
		}
	});

	var btnEliminardiscapacidad = new Ext.Button(
			{
				id : 'btnEliminardiscapacidad',
				x : 130,
				y : 470,
				width : 100,
				iconAlign : 'top',
				text : 'Eliminar',
				icon : 'icon/eliminar.gif',
				minWidth : 80,
				disabled : true,
				handler : function() {
					btnEditardiscapacidad.setDisabled(true);
					btnEliminardiscapacidad.setDisabled(true);
					Ext.MessageBox
							.confirm(
									'Cuidado!',
									'Esta seguro de eliminar la Discapacidad Seleccionada?'
											+ claveregistro,
									function(btn) {
										if (btn == 'yes') {
											var storeEliminadiscapacidad = new Ext.data.JsonStore(
													{
														url : 'formdiscapacidades/eliminardiscapacidad.htm'
													});
											storeEliminadiscapacidad
													.load({
														params : {
															KyDiscapacidad : claveregistro
														},
														callback : function() {
															storediscapacidades
																	.load();
														}
													});
										}
									});
				}
			});

	var btnEditardiscapacidad = new Ext.Button({
		id : 'btnEditardiscapacidad',
		x : 240,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Editar',
		icon : 'icon/editar.png',
		minWidth : 80,
		disabled : true,
		handler : function() {
			AbrirNuevadiscapacidad();
		}
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// LISTA DE Discapacidad //////////////////////////
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

	function formatosexo(val) {
		if (val == 0) {
			return '<span style="color:#000;font-size:11;">M</span>';
		}
		if (val == 1) {
			return '<span style="color:#000;font-size:11;">F</span>';
		}
	}

	var storediscapacidades = new Ext.data.JsonStore({
		url : 'formdiscapacidades/cargar.htm',
		root : 'data',
		totalProperty : 'total',
		paramNames : {
			start : 'offset',
			limit : 'size',
			sort : 'sort',
			dir : 'dir'
		},
		fields : [ 'KyDiscapacidad', 'Discapacidad', 'Estado' ]
	});
	storediscapacidades.load();

	var pagerdiscapacidades = new Ext.PagingToolbar({
		store : storediscapacidades, // <--grid and PagingToolbar using same
		// store
		displayInfo : true,
		displayMsg : 'Registro {0} al {1} de un total de {2}',
		emptyMsg : 'No hay registros que mostrar.',
		pageSize : 15
	});

	var myReaderdiscapacidades = new Ext.data.ArrayReader({}, [ {
		name : 'KyDiscapacidad'
	}, {
		name : 'Discapacidad'
	}, {
		name : 'Estado'
	} ]);

	var grid1discapacidades = new Ext.grid.GridPanel({
		title : 'Discapacidad',
		frame : false,
		store : storediscapacidades,
		reader : myReaderdiscapacidades,
		clicksToEdit : 1,
		columns : [ new Ext.grid.RowNumberer(), {
			header : 'KyDiscapacidad',
			width : 50,
			sortable : true,
			dataIndex : 'KyDiscapacidad',
			hidden : true
		}, {
			header : 'Discapacidad',
			width : 200,
			sortable : true,
			dataIndex : 'Discapacidad'
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
		bbar : [ pagerdiscapacidades, '->' ],
		border : false,
		stripeRows : true,
		loadMask : true
	});

	// *********FUNCION LEER DOBLE CLICK**********
	Ext.ns('clickgrid');
	clickgrid.gridclik = {
		init : function() {
			grid1discapacidades.on('rowdblclick', this.editRegistro);
			grid1discapacidades.on('rowclick', this.editMovie);
		},
		editMovie : function(grid1discapacidades, index, event) {
			var record = grid1discapacidades.getStore().getAt(index);
			claveregistro = record.get('KyDiscapacidad');
			txtKyDiscapacidad.setValue(record.get('KyDiscapacidad'));
			;
			txtDiscapacidad.setValue(record.get('Discapacidad'));

			if (record.get('Estado') == 0) {
				txtEstado.setValue("0.Inactivo");
			}
			if (record.get('Estado') == 1) {
				txtEstado.setValue("1.Activo");
			}

			btnEditardiscapacidad.enable();
			btnEliminardiscapacidad.enable();
		},
		editRegistro : function(grid1discapacidades, index, event) {
			var record = grid1discapacidades.getStore().getAt(index);
			claveregistro = record.get('KyDiscapacidad');
			btnEditardiscapacidad.enable();
			btnEliminardiscapacidad.enable();
			AbrirNuevadiscapacidad();
		}
	};
	Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

	var Frmdiscapacidades = new Ext.FormPanel({
		frame : true,
		layout : 'form',
		buttonAlign : 'right',
		renderTo : 'DivformExpediente',
		height : 500,
		bbar : [ '->', btnEliminardiscapacidad, btnEditardiscapacidad,
				btnNuevodiscapacidad ],
		items : [ grid1discapacidades ]
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// TXT PARA NUEVAS Discapacidad
	// //////////////////////////
	// //////////////////////// ///////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////

	var btnGuardardiscapacidad = new Ext.Button({
		id : 'btnGuardardiscapacidad',
		width : 80,
		heigth : 30,
		iconAlign : 'top',
		text : 'Guardar',
		icon : 'icon/disk.png',
		handler : function() {

			FrmNuevadiscapacidad.validarAccesoEspec();

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
			winNuevadiscapacidad.hide();
		}
	});

	var txtKyDiscapacidad = new Ext.form.TextField({
		fieldLabel : 'KyDiscapacidad',
		name : 'txtKyDiscapacidad',
		id : 'txtKyDiscapacidad',
		readOnly : true,
		width : 80
	});

	var txtDiscapacidad = new Ext.form.TextField({
		fieldLabel : 'Discapacidad',
		name : 'txtDiscapacidad',
		id : 'txtDiscapacidad',
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

	var FrmNuevadiscapacidad = new Ext.FormPanel(
			{
				frame : false,
				layout : 'form',
				bodyStyle : 'padding: 10px;',
				buttonAlign : 'right',
				items : [ txtKyDiscapacidad, txtDiscapacidad, txtEstado ],
				validarAccesoEspec : function() {
					if (this.getForm().isValid()) {
						this
								.getForm()
								.submit(
										{
											url : 'formdiscapacidades/editardiscapacidad.htm',// sessvars.idempresa,
											method : 'POST',
											waitTitle : 'Conectando',
											waitMsg : 'Intentando guardar el registro...',
											success : function(form, action) {
												winNuevadiscapacidad.hide();
												storediscapacidades.load();
												btnEditardiscapacidad
														.setDisabled(true);
												btnEliminardiscapacidad
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
														FrmNuevadiscapacidad
																.getForm()
																.reset();
													}
												}

											}
										});
					}
				}

			});

	function AbrirNuevadiscapacidad() {
		if (!winNuevadiscapacidad) {
			winNuevadiscapacidad = new Ext.Window({
				layout : 'fit',
				title : 'Nuevo/Editar Discapacidad',
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
				items : [ FrmNuevadiscapacidad ],
				tbar : [ '->', btnCerrar ],
				bbar : [ '->', btnGuardardiscapacidad ],
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
		winNuevadiscapacidad.show();
	}

}