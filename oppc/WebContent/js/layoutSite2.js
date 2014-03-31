Ext.require(['*']);

    Ext.onReady(function() {

        Ext.QuickTips.init();

        
        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
		//这是北边的panel
        var northPanel =  Ext.create('Ext.Component', {
			region: 'north',
			height: 50, // give north and south regions a height
			//autoEl: {
			//	tag: 'div',
			//	html:'<p>north - generally for menus, toolbars and/or advertisements </p>'
			//},
			loader: {
				url: 'index.jsp',
				autoLoad: true
			}
			
			
        });
		
		//这是南边的panel
		var southPanel = Ext.create('Ext.panel.Panel',{
			// lazily created panel (xtype:'panel' is default)
			region: 'south',
			contentEl: 'south',
			split: true,
			height: 100,
			minSize: 100,
			maxSize: 200,
			collapsible: true,
			collapsed: true,
			title: 'South',
			margins: '0 0 0 0'	
		});
		
		//这是东边的panel
		
		var eastPanel = Ext.create('Ext.tab.Panel',{
		
			//xtype: 'tabpanel',
			region: 'east',
			title: 'East Side',
			dockedItems: [{
				dock: 'top',
				xtype: 'toolbar',
				items: [ '->', {
				   xtype: 'button',
				   text: 'test',
				   tooltip: 'Test Button'
				}]
			},{
				dock: 'bottom',
				xtype: 'toolbar',
				items: [ '->', {
				   xtype: 'button',
				   text: 'test2',
				   tooltip: 'Test2 Button'
				}]
			}],
			animCollapse: true,
			collapsible: true,
			split: true,
			width: 225, // give east and west regions a width
			minSize: 175,
			maxSize: 400,
			margins: '0 5 0 0',
			activeTab: 1,
			tabPosition: 'bottom',
			items: [{
				html: '<p>A TabPanel component can be a region.</p>',
				title: 'A Tab',
				autoScroll: true
			    },
				Ext.create('Ext.grid.PropertyGrid', {
					title: 'Property Grid',
					closable: true,
					source: {
						"(name)": "Properties Grid",
						"grouping": false,
						"autoFitColumns": true,
						"productionQuality": false,
						"created": Ext.Date.parse('10/15/2006', 'm/d/Y'),
						"tested": false,
						"version": 0.01,
						"borderWidth": 1
					}
				})]
            
		});
        //这是tree节点的树，通过json传递
        var tree_store = new Ext.data.TreeStore({
    		id:'tree_store',
    		proxy: {
    			type: 'ajax',
    			url: 'TreeData.sp',
    			reader: {
    				type: 'json'
    			}
    		},
    		root: { expanded: false },//根节点不显示
    		autoLoad: true
    	}); 
		//这是中间的面板放在layout中
        var contentPanel =  Ext.create('Ext.tab.Panel', {
            region: 'center', // a center region is ALWAYS required for border layout
            deferredRender: false,
            id:'contentPanel',
            activeTab: 0,     // first tab initially active
            items: [{
                contentEl: 'center1',
                title: 'Close Me',
                closable: true,
                autoScroll: true
            }, {
                contentEl: 'center2',
                title: 'Center Panel',
                autoScroll: true
            }]
        });
		
		//这是treePanel
		var treePanel = Ext.create('Ext.tree.Panel', {
			//layout:'fit',
			stateId: 'navigation-panel',
			id: 'tree-panel',
			title: '拓扑结构',
			iconCls: 'nav',
			split: true,
			width: 200,
			minWidth: 175,
			maxWidth: 400,
			animCollapse: true,
			rootVisible: false,  //默认不显示根节点
			//singleExpand: true, //
			//containerScroll:true,
			useArrows: true,
			store: tree_store,
			//root: {expanded: true},
			tools: [{
				type: 'refresh',
				tooltip: '刷新',
				// hidden:true,
				handler: function() {//Ext.data.Store load
					tree_store.load({
						scope: this,
						callback: function(records, operation, success) {
							//Ext.Msg.alert('refresh success!');
							treePanel.getRootNode().eachChild(function(child) { child.expand(); });
						}
					});
				}
			}
			],
			collapsible: true,
			listeners: {
				'itemclick': function(view, record, item, index, e) {
					nodeId = record.raw.id; //获取点击的节点id
					//Ext.Msg.alert('text', nodeId);
					nodeText = record.raw.text; //获取点击的节点text
					//Ext.Msg.alert('info', nodeId + nodeText+"==="+tree_store.getNodeById(nodeId).hasChildNodes());
					if (tree_store.getNodeById(nodeId).hasChildNodes() === true) {					
					}
					else {					
						var n = contentPanel.getComponent(nodeId);
						if (!n) {
							var n = contentPanel.add({
								'id' : nodeId,
								'title' : nodeText,
								closable : true,
								autoLoad : {
									url : 'Welcome.sp',//根据页面的id变成动态的路径访问
									scripts : true
								} // 通过autoLoad属性载入目标页,如果要用到脚本,必须加上scripts属性
							});
						}
						contentPanel.setActiveTab(n);					
						//Ext.Msg.alert('设备管理', nodeId + nodeText);
					}
					//Ext.Msg.alert('text', nodeText);
				}
			}
		});
		
		//这是西边的panel
		var westPanel = Ext.create('Ext.panel.Panel',{
			
			region: 'west',
			stateId: 'navigation-panel',
			id: 'west-panel', // see Ext.getCmp() below
			title: 'West',
			split: true,
			width: 200,
			minWidth: 175,
			maxWidth: 400,
			collapsible: true,
			animCollapse: true,
			margins: '0 0 0 5',
			layout: 'accordion',
			items: [ treePanel,{
				title: '其他管理',
				html: '<p>Some settings in here.</p>',
				//contentEl:'west1',
				iconCls: 'settings'
			}]
		});
		//这是布局
        var viewport = Ext.create('Ext.Viewport', {
            id: 'border-example',
            layout: 'border',
            items: [
				northPanel, southPanel, eastPanel, westPanel,contentPanel
		    ]
        });
        
        
        // get a reference to the HTML element with id "hideit" and add a click listener to it
        Ext.get("hideit").on('click', function(){
            // get a reference to the Panel that was created with id = 'west-panel'
            var w = Ext.getCmp('west-panel');
            // expand or collapse that Panel based on its collapsed property state
            w.collapsed ? w.expand() : w.collapse();
        });
        
    });