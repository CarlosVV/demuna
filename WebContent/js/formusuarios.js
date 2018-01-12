if (txtKyNivelUsuario.getValue() == 0) {

	var FrmUsuarios = new Ext.Panel(
			{
				frame : true,
				layout : 'form',
				renderTo : 'DivformExpediente',
				html : '<center><h1>Lo Sentimos Ud. no tiene acceso a este modulo.<br/>Crear Usuarios No esta Disponible.</h1></center> ',
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
	var winNuevaUsuario, winUbigeo, claveregistro;

	var btnNuevoUsuario = new Ext.Button({
		id : 'btnNuevoUsuario',
		x : 20,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Nuevo',
		icon : 'icon/nuevo.png',
		minWidth : 80,
		handler : function() {
			var storeNuevaUsuario = new Ext.data.JsonStore({
				url : 'formusuarios/nuevousuario.htm'
			});
			storeNuevaUsuario.load({
				callback : function() {
					storeusuarios.load();
				}
			});
		}
	});

	var btnEliminarUsuario = new Ext.Button({
		id : 'btnEliminarUsuario',
		x : 130,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Eliminar',
		icon : 'icon/eliminar.gif',
		minWidth : 80,
		disabled : true,
		handler : function() {
			btnEditarUsuario.setDisabled(true);
			btnEliminarUsuario.setDisabled(true);
			Ext.MessageBox.confirm('Cuidado!',
					'Esta seguro de eliminar la Usuario Seleccionada?'
							+ claveregistro, function(btn) {
						if (btn == 'yes') {
							var storeEliminaUsuario = new Ext.data.JsonStore({
								url : 'formusuarios/eliminausuario.htm'
							});
							storeEliminaUsuario.load({
								params : {
									kyusuario : claveregistro
								},
								callback : function() {
									storeusuarios.load();
								}
							});
						}
					});
		}
	});

	var btnEditarUsuario = new Ext.Button({
		id : 'btnEditarUsuario',
		x : 240,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Editar',
		icon : 'icon/editar.png',
		minWidth : 80,
		disabled : true,
		handler : function() {
			AbrirNuevaUsuario();
		}
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// LISTA DE UsuarioS //////////////////////////
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

	function formatNivel(val) {
		if (val == 0) {
			return '<span style="color:#000;font-size:11;">' + val
					+ ':Operador</span>';
		}
		if (val == 1) {
			return '<span style="color:#000;font-size:11;">' + val
					+ ':Administrador</span>';
		}
	}

	var storeusuarios = new Ext.data.JsonStore({
		url : 'formusuarios/cargar.htm',
		root : 'data',
		totalProperty : 'total',
		paramNames : {
			start : 'offset',
			limit : 'size',
			sort : 'sort',
			dir : 'dir'
		},
		fields : [ 'kyusuario', 'nombres', 'appaterno', 'apmaterno', 'nombre',
				'email', 'usuario', 'clave', 'telefonos', 'nivel', 'estado',
				'KyDemuna' ]
	});
	storeusuarios.load();

	var pagerusuarios = new Ext.PagingToolbar({
		store : storeusuarios, // <--grid and PagingToolbar using same store
		displayInfo : true,
		displayMsg : 'Registro {0} al {1} de un total de {2}',
		emptyMsg : 'No hay registros que mostrar.',
		pageSize : 15
	});

	var myReaderUsuarios = new Ext.data.ArrayReader({}, [ {
		name : 'kyusuario'
	}, {
		name : 'usuario'
	}, {
		name : 'clave'
	}, {
		name : 'telefonos'
	}, {
		name : 'nivel'
	}, {
		name : 'estado'
	} ]);

	var grid1Usuarios = new Ext.grid.GridPanel({
		title : 'Usuarios',
		frame : false,
		store : storeusuarios,
		reader : myReaderUsuarios,
		clicksToEdit : 1,
		columns : [ new Ext.grid.RowNumberer(), {
			header : 'KyUsuario',
			width : 50,
			sortable : true,
			dataIndex : 'kyusuario',
			hidden : true
		}, {
			header : 'Nombres',
			width : 120,
			sortable : true,
			dataIndex : 'nombres',
			hidden : false
		}, {
			header : 'Ap.Paterno',
			width : 120,
			sortable : true,
			dataIndex : 'appaterno',
			hidden : false
		}, {
			header : 'Ap.Materno',
			width : 120,
			sortable : true,
			dataIndex : 'apmaterno',
			hidden : false
		}, {
			header : 'Email',
			width : 120,
			sortable : true,
			dataIndex : 'email',
			hidden : false
		}, {
			header : 'Usuarios',
			width : 100,
			sortable : true,
			dataIndex : 'usuario'
		}, {
			header : 'Claves',
			width : 50,
			sortable : true,
			dataIndex : 'clave',
			hidden : true
		}, {
			header : 'Telefonos',
			width : 60,
			sortable : true,
			renderer : ftosecundario,
			dataIndex : 'telefonos'
		}, {
			header : 'Nivel',
			width : 50,
			sortable : true,
			renderer : formatNivel,
			dataIndex : 'nivel'
		}, {
			header : 'Estado',
			width : 50,
			sortable : true,
			renderer : formatestado,
			dataIndex : 'estado'
		}, {
			header : 'KyDemuna',
			width : 50,
			sortable : true,
			dataIndex : 'KyDemuna',
			hidden : true
		} ],
		viewConfig : {
			forceFit : true
		},
		autoWidth : true,
		height : 450,
		frame : false,
		bbar : [ pagerusuarios, '->' ],
		tbar : [],
		border : false,
		stripeRows : true,
		loadMask : true
	});

	// *********FUNCION LEER DOBLE CLICK**********
	Ext.ns('clickgrid');
	clickgrid.gridclik = {
		init : function() {
			grid1Usuarios.on('rowdblclick', this.editRegistro);
			grid1Usuarios.on('rowclick', this.editMovie);
		},
		editMovie : function(grid1Usuarios, index, event) {
			var record = grid1Usuarios.getStore().getAt(index);
			claveregistro = record.get('kyusuario');

			txtKyUsuario.setValue(record.get('kyusuario'));
			txtUsuario.setValue(record.get('usuario'));
			txtClave.setValue(record.get('clave'));
			txtUsuNombres.setValue(record.get('nombres'));
			txtUsuAppaterno.setValue(record.get('appaterno'));
			txtUsuApmaterno.setValue(record.get('apmaterno'));
			txtEmail.setValue(record.get('email'));
			txtTelefonos.setValue(record.get('telefonos'));

			comboTipoDemuna.setValue(record.get('KyDemuna'));

			if (record.get('estado') == 0) {
				txtEstado.setValue("0.Inactivo");
			}
			if (record.get('estado') == 1) {
				txtEstado.setValue("1.Activo");
			}

			if (record.get('nivel') == 0) {
				txtNivel.setValue("0.Operador");
			}
			if (record.get('nivel') == 1) {
				txtNivel.setValue("1.Administrador");
			}

			btnEditarUsuario.enable();
			btnEliminarUsuario.enable();
		},
		editRegistro : function(grid1Usuarios, index, event) {
			var record = grid1Usuarios.getStore().getAt(index);
			claveregistro = record.get('kyusuario');
			btnEditarUsuario.enable();
			btnEliminarUsuario.enable();
			AbrirNuevaUsuario();
		}
	};
	Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

	var FrmUsuarios = new Ext.FormPanel({
		frame : true,
		layout : 'form',
		buttonAlign : 'right',
		renderTo : 'DivformExpediente',
		height : 500,
		bbar : [ '->', btnEliminarUsuario, btnEditarUsuario, btnNuevoUsuario ],
		items : [ grid1Usuarios ]
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// TXT PARA NUEVAS UsuarioS
	// //////////////////////////
	// //////////////////////// ///////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////

	var btnGuardarUsuario = new Ext.Button({
		id : 'btnGuardarUsuario',
		width : 80,
		heigth : 30,
		iconAlign : 'top',
		text : 'Guardar',
		icon : 'icon/disk.png',
		handler : function() {
			FrmNuevaUsuario.validarAccesoEspec();
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
			winNuevaUsuario.hide();
		}
	});

	var txtKyUsuario = new Ext.form.TextField({
		fieldLabel : 'KyUsuario',
		name : 'txtKyUsuario',
		readOnly : true,
		id : 'txtKyUsuario',
		width : 80
	});

	var txtUsuario = new Ext.form.TextField({
		fieldLabel : 'Usuario',
		name : 'txtUsuario',
		id : 'txtUsuario',
		width : 100
	});

	var txtClave = new Ext.form.TextField({
		fieldLabel : 'Clave',
		name : 'txtClave',
		id : 'txtClave',
		width : 100
	});

	var txtTelefonos = new Ext.form.TextField({
		fieldLabel : 'Telefono',
		name : 'txtTelefonos',
		id : 'txtTelefonos',
		width : 100
	});

	var txtUsuNombres = new Ext.form.TextField({
		fieldLabel : 'Nombres',
		name : 'txtUsuNombres',
		id : 'txtUsuNombres',
		width : 350
	});

	var txtUsuAppaterno = new Ext.form.TextField({
		fieldLabel : 'Ap.Paterno',
		name : 'txtUsuAppaterno',
		id : 'txtUsuAppaterno',
		width : 350
	});

	var txtUsuApmaterno = new Ext.form.TextField({
		fieldLabel : 'Ap.Materno',
		name : 'txtUsuApmaterno',
		id : 'txtUsuApmaterno',
		width : 350
	});

	var txtEmail = new Ext.form.TextField({
		fieldLabel : 'E-mail',
		name : 'txtEmail',
		id : 'txtEmail',
		width : 350
	});

	var storeTipoDemuna = new Ext.data.JsonStore({
		url : 'formusuarios/cargartipodemuna.htm',
		root : 'dataTipoDemuna',
		totalProperty : 'total',
		paramNames : {
			start : 'offset',
			limit : 'size',
			sort : 'sort',
			dir : 'dir'
		},
		fields : [ 'KyDemuna', 'Nombre' ]
	});
	storeTipoDemuna.load();

	var comboTipoDemuna = new Ext.form.ComboBox({
		fieldLabel : 'Demuna',
		name : 'comboTipoDemuna',
		forceSelection : true,
		store : storeTipoDemuna,
		emptyText : 'Tipo Demuna',
		triggerAction : 'all',
		editable : false,
		displayField : 'Nombre',
		mode : 'local',
		valueField : 'KyDemuna',
		allowBlank : false,
		blankText : 'Tipo Demuna',
		hiddenName : 'comboTipoDemunaGuardar',
		width : 350
	});

	var datanivel = [ '0.Operador', '1.Administrador' ];
	var txtNivel = new Ext.form.ComboBox({
		fieldLabel : 'Nivel',
		name : 'txtNivel',
		forceSelection : true,
		allowBlank : false,
		blankText : 'Seleecione Nivel',
		store : datanivel,
		emptyText : 'Seleccione Nivel',
		triggerAction : 'all',
		editable : false,
		value : '0.Operador',
		width : 150
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
		value : '0.Inactivo',
		width : 150
	});

	var fieldgrupo01 = new Ext.form.FieldSet({
		title : 'Datos Principales',
		collapsible : false,
		autoHeight : true,
		defaultType : 'textfield',
		items : [ txtKyUsuario, txtUsuNombres, txtUsuAppaterno, txtUsuApmaterno,
				txtEmail, txtUsuario, txtClave, txtTelefonos ]
	});

	var fieldgrupo02 = new Ext.form.FieldSet({
		title : 'Estado',
		collapsible : false,
		autoHeight : true,
		defaultType : 'textfield',
		items : [ txtEstado ]
	});
	var fieldgrupo03 = new Ext.form.FieldSet({
		title : 'Nivel',
		collapsible : false,
		autoHeight : true,
		defaultType : 'textfield',
		items : [ txtNivel ]
	});
	var fieldgrupo04 = new Ext.form.FieldSet({
		title : 'Demuna',
		collapsible : false,
		autoHeight : true,
		defaultType : 'textfield',
		items : [ comboTipoDemuna ]
	});

	var FrmNuevaUsuario = new Ext.FormPanel(
			{
				frame : false,
				layout : 'form',
				bodyStyle : 'padding: 10px;',
				buttonAlign : 'right',

				items : [ fieldgrupo01, fieldgrupo04, fieldgrupo03,
						fieldgrupo02 ],

				validarAccesoEspec : function() {
					if (this.getForm().isValid()) {
						this
								.getForm()
								.submit(
										{
											url : 'formusuarios/editarusuario.htm',
											method : 'POST',
											waitTitle : 'Conectando',
											waitMsg : 'Intentando guardar el registro...',
											success : function(form, action) {
												winNuevaUsuario.hide();
												storeusuarios.load();
												btnEditarUsuario
														.setDisabled(true);
												btnEliminarUsuario
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
														FrmNuevaUsuario
																.getForm()
																.reset();
													}
												}

											}
										});
					}
				}

			});

	function AbrirNuevaUsuario() {
		if (!winNuevaUsuario) {
			winNuevaUsuario = new Ext.Window({
				layout : 'fit',
				title : 'Nuevo/Editar Usuario',
				width : 600,
				height : 550,
				resizable : false,
				closeAction : 'hide',
				closable : true,
				draggable : true,
				plain : true,
				border : false,
				modal : true,
				style : 'margin:10px;',
				items : [ FrmNuevaUsuario ],
				tbar : [ '->', btnCerrar ],
				bbar : [ '->', btnGuardarUsuario ],
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
		winNuevaUsuario.show();
	}
}