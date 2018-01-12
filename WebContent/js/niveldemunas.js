var winNivelDemunas;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								//TIPO DE DEMUNAS
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var storeTipoDemunas = new Ext.data.JsonStore({
					url:'niveldemunas/cargarTipoDemuna.htm',
					root: 'data',
					totalProperty: 'total',
					paramNames: {
						start : 'offset',  
						limit : 'size',  
						sort : 'sort',   
						dir : 'dir'      
					},
					fields: ['KyTipoDemuna','Descripcion']
				});		
	
		storeTipoDemunas.load();
		
		var CboTipoDemunas=new Ext.form.ComboBox({
				fieldLabel:'Tipo Demuna',
				name:'CboTipoDemunas', 
				forceSelection: true,  
				store: storeTipoDemunas,
				hiddenName:'CboTipoDemunasId',
				emptyText:'Tipo Demuna',  
				triggerAction: 'all',  
				editable:false, 
				mode: 'local',
				displayField:'Descripcion',  
				valueField: 'KyTipoDemuna',
				allowBlank: false,
				width:250,
				blankText: 'Selecciona..',
				listeners: {
								'select': function(cmb, data, idx) 
								{			
									CboDepartamentos.setValue('');
									storeDepartamentos.load({params:{tipo:CboTipoDemunas.getValue()}});									
								}
							  }	
			}); 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								//DEMUNAS NACIONALES
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var storeDepartamentos = new Ext.data.JsonStore({
						url:'niveldemunas/cargarDemunasNacionales.htm',
						root: 'data',
						totalProperty: 'total',
						paramNames: {
							start : 'offset',  
							limit : 'size',  
							sort : 'sort',   
							dir : 'dir'      
						},
						fields: ['KyDemuna','Nombre']
					});			
		
	var CboDepartamentos=new Ext.form.ComboBox({
			fieldLabel:'Demuna Superior',
			name:'CboDepartamentos', 
			forceSelection: true,  
			store: storeDepartamentos,
			hiddenName:'comboDepartamentosId',
			emptyText:'Seleccione Demuna Superior', 
			mode: 'local',
			triggerAction: 'all',  
			editable:false,  
			displayField:'Nombre',  
			valueField: 'KyDemuna',			
			width:350,
			blankText: 'Selecciona..',
			listeners: {
							'select': function(cmb, data, idx) 
							{									
								//storeprovincias.load({params:{dpto:comboDepartamentos.getValue()}});									
							}
						  }	
		}); 
		
		var btnAceptarNivel = new Ext.Button({
					id: 'btnAceptarNivel',		
					width:80,
					heigth:30,
					iconAlign: 'top',
					text: 'Aceptar',
					icon:'./icon/ok111.png',			
					handler:function(){		
					
					
						txtNivel.setValue(CboTipoDemunas.getValue());
						txtDependencia.setValue(CboDepartamentos.getValue());					
						if (CboDepartamentos.getValue()=='')
							{
								txtDependencia.setValue('1');
							}
						winNivelDemunas.hide();
					}
				});
		
	
		
		function AbrirNivelDemunas(){
			if (!winNivelDemunas) {
				winNivelDemunas = new Ext.Window({
					layout: 'form',
					bodyStyle:'padding: 10px;background-color:#FFFFFF;',
					title:'Nivel de Demuna',
					width: 500,
					height:250,					
					resizable: false,
					closeAction: 'hide',
					closable: true,
					draggable: true,				
					plain: false,
					border:false,
					modal:false,
					style:'margin:10px;',
					items: [CboTipoDemunas, CboDepartamentos],
					bbar:['->',btnAceptarNivel],					
					listeners: {
						show: function(){}							
					}
				});
			}
			winNivelDemunas.show();
		}