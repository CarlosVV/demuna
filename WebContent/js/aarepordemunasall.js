var winExpediente;

		var txtFechaIni = new Ext.form.DateField({
					style:'margin:1px;',
					fieldLabel:'Del',						   
					format: 'd-m-Y', 
					width: 120,
					name: 'txtFechaIni'
					
			 });

		var txtFechaFin = new Ext.form.DateField({
							style:'margin:1px;',
							fieldLabel:'Al',						   
							format: 'd-m-Y', 
							width: 120,
							name: 'txtFechaFin'					
					 });
		
		var btnMostrarFechas = new Ext.Button({
						id: 'btnMostrarFechas',		
						width:50,
						heigth:40,							
						icon:'./icon/ok111.png',			
						handler:function(){																	
							storedemunas.load({params:{ini:txtFechaIni.value,fin:txtFechaFin.value}});
						}
					});	
		
		
	/////////////////////////////////////////////////////////////////////////////////////////////////////// 
	///////////////////////////                                                 /////////////////////////// 
	//////////////////////////             LISTA DE DEMUNAS                      //////////////////////////	
	//////////////////////////                                                  ///////////////////////////	
	/////////////////////////////////////////////////////////////////////////////////////////////////////// 
		
			function ftoprincipal(val){        
					  return '<span style="color:#3366CC; font-weight:bold;">' + val + '</span>';
					}
			function ftosecundario(val){        
				  return '<span style="color:#0099CC;">' + val + '</span>';
				}
			function ftogris(val){        
				  return '<span style="color:#CCCCCC;">' + val + '</span>';
				}
			function ftoalingder(val){        
				  return '<div style="text-align:right;">' + val + '</div>';
				}
			function ftoalingcen(val){        
				  return '<div style="text-align:center;">' + val + '</div>';
				}
			
			function formatestado(val){ 
				/* 0 Activo. 1. inactivo */
				 if(val==0)
					 {	
					  return '<span style="color:#FF0000;font-size:11;"> '+val+' : Inactivo</span>';
					 }
				if(val==1)
					 {	
					  return '<span style="color:#000;font-size:11;"> '+val+' : Activo </span>';
					 }				
				}		
				
			function formatosexo(val){ 				
				 if(val==0)
					 {	
					  return '<span style="color:#000;font-size:11;">M</span>';
					 }
				if(val==1)
					 {	
					  return '<span style="color:#000;font-size:11;">F</span>';
					 }				
				}	
		//url:'aarepordemunasall.php?cargar=true&demuna='+txtKyDemunaIni.getValue(),		
	 	var storedemunas = new Ext.data.JsonStore({
					url:'reportes/aarepordemunasall.htm',
					root: 'data',
					totalProperty: 'total',
					paramNames: {
						start : 'offset',  
						limit : 'size',  
						sort : 'sort',   
						dir : 'dir'      
					},
					fields: ['Demuna','Casos']
				});		
		storedemunas.load();						
				
		
		var pagerdemunas = new Ext.PagingToolbar({
			store: storedemunas, // <--grid and PagingToolbar using same store
			displayInfo: true,
			displayMsg: 'Registro {0} al {1} de un total de {2}',
			emptyMsg: 'No hay registros que mostrar.',
			pageSize: 15
		});
		
		var myReaderDemunas = new Ext.data.ArrayReader({},[
			{name: 'Demuna'},
			{name: 'Casos'}				
		]);
							
		var grid1Demunas = new Ext.grid.GridPanel({			
			frame:false,
			store: storedemunas, 
			reader: myReaderDemunas,			
			clicksToEdit: 1,			
			columns:[								 					 
				new Ext.grid.RowNumberer(),					
				{header: 'Demuna', 	width:150, sortable: true, dataIndex: 'Demuna', hidden:false},	
				{header: '# Casos', 	width:300, sortable: true, dataIndex: 'Casos', hidden:false}			
			],
			viewConfig: {
				forceFit: true
			},			
			autoWidth:true,
			height: 450,
			frame: false,			
			bbar:[pagerdemunas,'->'],
			tbar:[txtFechaIni,txtFechaFin,btnMostrarFechas,{
					text: 'Mostrar Todo',
					handler: function(){
											storedemunas.load();				
											store.loadData(storedemunas);        
										}
        		}],
			border: false,
			stripeRows: true,
			loadMask: true
		});
		
		

		function AbrirExpedientes(){		
				if (!winExpediente) {
					winExpediente = new Ext.Panel({
						renderTo : 'DivformExpediente',
						layout: 'fit',
						width: 1000,
						height: 509,		
						title: 'Reporte de #Casos por Demuna',			
						resizable: false,
						closeAction: 'hide',
						closable: false,						
						draggable: true,
						plain: false,
						border:false,
						modal:false,
						items: [grid1Demunas],							
						listeners: {
							show: function(){														
							}
						}
					});
				}			
				winExpediente.show();				
			};		
		