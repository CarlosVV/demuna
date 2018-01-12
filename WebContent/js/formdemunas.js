if (txtKyNivelUsuario.getValue() == 0) {

	var FrmUsuarios = new Ext.Panel(
			{
				frame : true,
				layout : 'form',
				renderTo : 'DivformExpediente',
				html : '<center><h1>Lo Sentimos Ud. no tiene acceso a este modulo.<br/>Crear Dependencias No esta Disponible.</h1></center> ',
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

	var winNuevaDemuna, winUbigeo, winCargarImagen, claveregistro;

	// //////////////////////////////////////CARGAR
	// LOGO///////////////////////////////////////////////

	var dataSINO = [ 'SI', 'NO' ];
	var cboSINO = new Ext.form.ComboBox({
		fieldLabel : 'Conciliación?',
		name : 'cboSINO',
		forceSelection : true,
		allowBlank : false,
		store : dataSINO,
		triggerAction : 'all',
		editable : false,
		width : 50,
		value : 'NO'
	});

	var txtNroCecte = new Ext.form.TextField({
		fieldLabel : '#Resolucion',
		name : 'txtNroCecte',
		readOnly : false,
		width : 350
	// disabled:true
	});

	var btnImagen = new Ext.Button({
		id : 'btnImagen',
		x : 240,
		y : 470,
		width : 50,
		scale : 'large',
		// text : 'Imagen',
		iconAlign : 'top',
		icon : 'icon/jpeg.png',
		minWidth : 80,
		handler : function() {
			// txtId2.setValue(txtKyDemuna.getValue());
			cargarImagen();
		}
	});

	var txtId2 = new Ext.form.TextField({
		fieldLabel : 'Codigo',
		name : 'txtId2',
		readOnly : true,
		width : 100
	// disabled:true
	});

	var fp = new Ext.FormPanel({
		frame : true,
		layout : 'form',
		bodyStyle : 'padding: 10px',
		buttonAlign : 'right',
		fileUpload : true,
		items : [ txtId2, {
			xtype : 'fileuploadfield',
			id : 'file',
			name : 'file',
			width : 300,
			emptyText : 'Seleccione una imagen..',
			fieldLabel : 'Imagen1',
			buttonText : 'Seleccionar',
			buttonCfg : {
				iconCls : 'upload-icon'
			}
		} ],
		buttons : [ {
			text : 'Guardar',
			id : 'btnGuardar',
			x : 240,
			y : 470,
			width : 80,
			heigth : 30,
			iconAlign : 'top',
			icon : './icon/disk.png',
			handler : function() {
				if (fp.getForm().isValid()) {
					fp.getForm().submit(
							{
								url : 'cargarimagen.htm',
								waitMsg : 'Guardando Imagen...',
								success : function(form, action) {

									if (action.result.success) {
										Ext.Msg.alert('Conexion Exitosa',
												action.result.msg, function() {
													winCargarImagen.hide();
												});
									} else {
										Ext.Msg.alert('Conexion Fallida',
												action.result.errors);
									}

								},
								failure : function(form, action) {
									Ext.MessageBox.alert('Error al grabar',
											action.result.errors);
								}

							});

				}
			}
		} ]
	});

	function cargarImagen() {
		if (!winCargarImagen) {
			winCargarImagen = new Ext.Window({
				layout : 'fit',
				width : 620,
				height : 150,
				title : 'Cargar Banner Cabecera',
				resizable : false,
				closeAction : 'hide',
				closable : true,
				draggable : true,
				plain : true,
				border : false,
				modal : true,
				style : 'margin:10px;',
				items : [ fp ],
				listeners : {
					show : function() {
						txtId2.setValue(txtKyDemuna.getValue());
					}
				}
			});
		}
		winCargarImagen.show();
	}

	//
	//
	//
	/** *************FIN CARGAR LOGO****************** */

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
		url : 'formexpedientes/cargarProvincias.htm', // +sessvars.idempresa,
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

	var btnNuevoDemuna = new Ext.Button({
		id : 'btnNuevoDemuna',
		x : 20,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Nuevo',
		icon : 'icon/nuevo.png',
		minWidth : 80,
		handler : function() {

			var storeNuevaDemuna = new Ext.data.JsonStore({
				url : 'formdemunas/nuevademuna.htm'
			});
			storeNuevaDemuna.load({
				callback : function() {
					storedemunas.load();
				}
			});
		}
	});

	var btnEliminarDemuna = new Ext.Button({
		id : 'btnEliminarDemuna',
		x : 130,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Eliminar',
		icon : 'icon/eliminar.gif',
		minWidth : 80,
		disabled : true,
		handler : function() {
			btnEditarDemuna.setDisabled(true);
			btnEliminarDemuna.setDisabled(true);
			Ext.MessageBox.confirm('Cuidado!',
					'Esta seguro de eliminar la Demuna Seleccionada?'
							+ claveregistro, function(btn) {
						if (btn == 'yes') {
							var storeEliminaDemuna = new Ext.data.JsonStore({
								url : 'formdemunas/eliminardemuna.htm'
							});
							storeEliminaDemuna.load({
								params : {
									KyDemuna : claveregistro
								},
								callback : function() {
									storedemunas.load();
								}
							});
						}
					});
		}
	});

	var btnEditarDemuna = new Ext.Button({
		id : 'btnEditarDemuna',
		x : 240,
		y : 470,
		width : 100,
		iconAlign : 'top',
		text : 'Editar',
		icon : 'icon/editar.png',
		minWidth : 80,
		disabled : true,
		handler : function() {
			AbrirNuevaDemuna();
		}
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// LISTA DE DEMUNAS //////////////////////////
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

	var storedemunas = new Ext.data.JsonStore({
		url : 'formdemunas/cargar.htm',
		root : 'data',
		totalProperty : 'total',
		paramNames : {
			start : 'offset',
			limit : 'size',
			sort : 'sort',
			dir : 'dir'
		},
		fields : [ 'KyDemuna', 'KyTipoDemuna', 'Dep1', 'MimdesNro', 'Nombre',
				'Ubicacion', 'Direccion', 'Referencia', 'Telefono', 'Correo',
				'FechaCreacion', 'ResolucionNro', 'Coordinador', 'Estado',
				'Cecte', 'NroCecte' ]
	});
	storedemunas.load();

	var pagerdemunas = new Ext.PagingToolbar({
		store : storedemunas, // <--grid and PagingToolbar using same
		// store
		displayInfo : true,
		displayMsg : 'Registro {0} al {1} de un total de {2}',
		emptyMsg : 'No hay registros que mostrar.',
		pageSize : 15
	});

	var myReaderDemunas = new Ext.data.ArrayReader({}, [ {
		name : 'KyDemuna'
	}, {
		name : 'MimdesNro'
	}, {
		name : 'Nombre'
	}, {
		name : 'Ubicacion'
	}, {
		name : 'Direccion'
	}, {
		name : 'Referencia'
	}, {
		name : 'Telefono'
	}, {
		name : 'Correo'
	}, {
		name : 'FechaCreacion'
	}, {
		name : 'ResolucionNro'
	}, {
		name : 'Coordinador'
	}, {
		name : 'Estado'
	} ]);

	var grid1Demunas = new Ext.grid.GridPanel({
		title : 'Demunas',
		frame : false,
		store : storedemunas,
		reader : myReaderDemunas,
		clicksToEdit : 1,
		columns : [ new Ext.grid.RowNumberer(), {
			header : 'KyDemuna',
			width : 50,
			sortable : true,
			dataIndex : 'KyDemuna',
			hidden : true
		}, {
			header : 'KyTipoDemuna',
			width : 50,
			sortable : true,
			dataIndex : 'KyTipoDemuna',
			hidden : true
		}, {
			header : 'Dependencia',
			width : 50,
			sortable : true,
			dataIndex : 'Dep1',
			hidden : true
		}, {
			header : 'MimdesNro',
			width : 120,
			sortable : true,
			dataIndex : 'MimdesNro'
		}, {
			header : 'Nombre',
			width : 120,
			sortable : true,
			renderer : ftosecundario,
			dataIndex : 'Nombre'
		}, {
			header : 'Ubicacion',
			width : 60,
			sortable : true,
			dataIndex : 'Ubicacion'
		}, {
			header : 'Direccion',
			width : 80,
			sortable : true,
			dataIndex : 'Direccion'
		}, {
			header : 'Referencia',
			width : 100,
			sortable : true,
			dataIndex : 'Referencia',
			hidden : true
		}, {
			header : 'Telefono',
			width : 60,
			sortable : true,
			dataIndex : 'Telefono'
		}, {
			header : 'Correo',
			width : 100,
			sortable : true,
			dataIndex : 'Correo'
		}, {
			header : 'Creación',
			width : 60,
			format : 'd-m-Y',
			sortable : true,
			dataIndex : 'FechaCreacion',
			hidden : true
		}, {
			header : 'ResolucionNro',
			width : 60,
			sortable : true,
			dataIndex : 'ResolucionNro',
			hidden : true
		}, {
			header : 'Cecte?',
			width : 60,
			sortable : true,
			dataIndex : 'Cecte'
		}, {
			header : 'NroCecte',
			width : 60,
			sortable : true,
			dataIndex : 'NroCecte',
			hidden : true
		}, {
			header : 'Coordinador',
			width : 60,
			sortable : true,
			dataIndex : 'Coordinador'
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
		bbar : [ pagerdemunas, '->' ],
		tbar : [ btnImagen ],
		border : false,
		stripeRows : true,
		loadMask : true
	});

	// *********FUNCION LEER DOBLE CLICK**********
	Ext.ns('clickgrid');
	clickgrid.gridclik = {
		init : function() {
			grid1Demunas.on('rowdblclick', this.editRegistro);
			grid1Demunas.on('rowclick', this.editMovie);
		},
		editMovie : function(grid1Demunas, index, event) {
			var record = grid1Demunas.getStore().getAt(index);
			claveregistro = record.get('KyDemuna');

			txtKyDemuna.setValue(record.get('KyDemuna'));
			txtNroMimdes.setValue(record.get('MimdesNro'));
			txtNivel.setValue(record.get('KyTipoDemuna'));
			txtDependencia.setValue(record.get('Dep1'));
			txtResolucion.setValue(record.get('ResolucionNro'));
			txtNombre.setValue(record.get('Nombre'));
			txtUbicacion.setValue(record.get('Ubicacion'));
			txtDireccion.setValue(record.get('Direccion'));
			txtReferencia.setValue(record.get('Referencia'));
			txtTelefono.setValue(record.get('Telefono'));
			txtCorreo.setValue(record.get('Correo'));
			txtFecha.setValue(record.get('FechaCreacion'));
			txtCordinador.setValue(record.get('Coordinador'));
			txtNroCecte.setValue(record.get('NroCecte'));
			cboSINO.setValue(record.get('Cecte'));

			CboTipoDemunas.setValue(record.get('KyTipoDemuna'));
			CboDepartamentos.setValue('');
			storeDepartamentos.load({
				params : {
					tipo : CboTipoDemunas.getValue()
				}
			});
			CboDepartamentos.setValue(record.get('Dep1'));

			if (record.get('Estado') == 0) {
				txtEstado.setValue("0.Inactivo");
			}
			if (record.get('Estado') == 1) {
				txtEstado.setValue("1.Activo");
			}

			btnEditarDemuna.enable();
			btnEliminarDemuna.enable();
		},
		editRegistro : function(grid1Demunas, index, event) {
			var record = grid1Demunas.getStore().getAt(index);
			claveregistro = record.get('KyDemuna');
			btnEditarDemuna.enable();
			btnEliminarDemuna.enable();
			AbrirNuevaDemuna();
		}
	};
	Ext.onReady(clickgrid.gridclik.init, clickgrid.gridclik);

	var FrmDemunas = new Ext.FormPanel({
		frame : true,
		layout : 'form',
		buttonAlign : 'right',
		renderTo : 'DivformExpediente',
		height : 500,
		bbar : [ '->', btnEliminarDemuna, btnEditarDemuna, btnNuevoDemuna ],
		items : [ grid1Demunas ]
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// ///////////////////////////
	// //////////////////////// TXT PARA NUEVAS DEMUNAS
	// //////////////////////////
	// //////////////////////// ///////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////

	var btnGuardarDemuna = new Ext.Button({
		id : 'btnGuardarDemuna',
		width : 80,
		heigth : 30,
		iconAlign : 'top',
		text : 'Guardar',
		icon : 'icon/disk.png',
		handler : function() {

			txtNivel.setValue(CboTipoDemunas.getValue());
			txtDependencia.setValue(CboDepartamentos.getValue());
			if (CboDepartamentos.getValue() == '') {
				txtDependencia.setValue('1');
			}

			FrmNuevaDemuna.validarAccesoEspec();

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
			winNuevaDemuna.hide();
		}
	});

	var txtKyDemuna = new Ext.form.TextField({
		fieldLabel : 'KyDemuna',
		name : 'txtKyDemuna',
		id : 'txtKyDemuna',
		width : 80
	});

	var txtNroMimdes = new Ext.form.TextField({
		fieldLabel : 'Nro.Reg.DEMUNA',
		name : 'txtNroMimdes',
		id : 'txtNroMimdes',
		width : 350
	});

	var txtNombre = new Ext.form.TextField({
		fieldLabel : 'Nombre Demuna',
		name : 'txtNombre',
		id : 'txtNombre',
		width : 350
	});

	var btnUbigeo = new Ext.Button({
		id : 'btnUbigeo',
		width : 100,
		text : 'Ubicación',
		handler : function() {

			AbrirUbigeo();
		}
	});

	var btnNivelDemuna = new Ext.Button({
		id : 'btnNivelDemuna',
		width : 100,
		text : 'Nivel',
		handler : function() {
			AbrirNivelDemunas();
		}
	});

	var txtNivel = new Ext.form.Hidden({
		fieldLabel : 'Nivel',
		name : 'txtNivel',
		id : 'txtNivel',
		width : 100
	});

	var txtDependencia = new Ext.form.Hidden({
		fieldLabel : 'Dependencia',
		name : 'txtDependencia',
		id : 'txtDependencia',
		width : 100
	});

	var txtUbicacion = new Ext.form.TextField({
		fieldLabel : 'Ubicación',
		name : 'txtUbicacion',
		id : 'txtUbicacion',
		width : 100
	});

	var txtDireccion = new Ext.form.TextField({
		fieldLabel : 'Dirección',
		name : 'txtDireccion',
		id : 'txtDireccion',
		width : 350
	});

	var txtReferencia = new Ext.form.TextField({
		fieldLabel : 'Referencia',
		name : 'txtReferencia',
		id : 'txtReferencia',
		width : 350
	});

	var txtTelefono = new Ext.form.TextField({
		fieldLabel : 'Telefono',
		name : 'txtTelefono',
		id : 'txtTelefono',
		width : 120
	});

	var txtCorreo = new Ext.form.TextField({
		fieldLabel : 'Email',
		name : 'txtCorreo',
		id : 'txtCorreo',
		width : 300,
		vtype : 'email',
		allowBlank : false,
		blankText : 'Email requerido'
	});

	var txtFecha = new Ext.form.DateField({
		fieldLabel : 'Fecha.Creación',
		name : 'txtFecha',
		id : 'txtFecha',
		format : 'd-m-Y',
		width : 120
	});

	var txtCordinador = new Ext.form.TextField({
		fieldLabel : 'Coordinador',
		name : 'txtCordinador',
		id : 'txtCordinador',
		width : 350
	});

	var txtResolucion = new Ext.form.TextField({
		fieldLabel : 'Nro.Resolución',
		name : 'txtResolucion',
		id : 'txtResolucion',
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

	var fieldgrupo01 = new Ext.form.FieldSet({
		title : 'Datos Principales',
		collapsible : false,
		autoHeight : true,
		defaultType : 'textfield',
		items : [ txtKyDemuna, txtNivel, txtDependencia, CboTipoDemunas,
				CboDepartamentos, txtNroMimdes, txtResolucion, txtNombre ]
	});

	var fieldgrupo02 = new Ext.form.FieldSet({
		title : 'Datos de Ubicación',
		collapsible : false,
		autoHeight : true,
		defaultType : 'textfield',
		items : [ btnUbigeo, txtUbicacion, txtDireccion, txtReferencia,
				txtTelefono, txtCorreo ]
	});

	var fieldgrupo03 = new Ext.form.FieldSet(
			{
				title : 'Conciliación Extrajudicial con Titulo de Ejecucion y  Nro Resolución.',
				collapsible : false,
				autoHeight : true,
				defaultType : 'textfield',
				items : [ cboSINO, txtNroCecte ]
			});

	var FrmNuevaDemuna = new Ext.FormPanel(
			{
				frame : false,
				layout : 'form',
				bodyStyle : 'padding: 10px;',
				buttonAlign : 'right',

				items : [ fieldgrupo01, fieldgrupo02, fieldgrupo03, txtFecha,
						txtCordinador, txtEstado ],

				validarAccesoEspec : function() {
					if (this.getForm().isValid()) {
						this
								.getForm()
								.submit(
										{
											url : 'formdemunas/editardemuna.htm',// sessvars.idempresa,
											method : 'POST',
											waitTitle : 'Conectando',
											waitMsg : 'Intentando guardar el registro...',
											success : function(form, action) {
												// Ext.Msg.alert('Conexion
												// Exitosa',
												// action.result.msg,function(){
												winNuevaDemuna.hide();
												storedemunas.load();
												btnEditarDemuna
														.setDisabled(true);
												btnEliminarDemuna
														.setDisabled(true);

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
														FrmNuevaDemuna
																.getForm()
																.reset();
													}
												}

											}
										});
					}
				}

			});

	function AbrirNuevaDemuna() {
		if (!winNuevaDemuna) {
			winNuevaDemuna = new Ext.Window({
				layout : 'fit',
				title : 'Nuevo/Editar Demuna',
				width : 600,
				height : 700,
				resizable : false,
				closeAction : 'hide',
				closable : true,
				draggable : true,
				plain : true,
				border : false,
				modal : true,
				style : 'margin:10px;',
				items : [ FrmNuevaDemuna ],
				tbar : [ '->', btnCerrar ],
				bbar : [ '->', btnGuardarDemuna ],
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
		winNuevaDemuna.show();
	}

}