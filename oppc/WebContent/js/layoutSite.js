Ext.require(['*']);

    Ext.onReady(function() {

        Ext.QuickTips.init();

        // NOTE: This is an example showing simple state management. During development,
        // it is generally best to disable state management as dynamically-generated ids
        // can change across page loads, leading to unpredictable results.  The developer
        // should ensure that stable state ids are set for stateful components in real apps.
        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
        /*var store2 = new Ext.data.TreeStore({
            model: 'Item',
            root: {
                text: 'Root 2',
                expanded: true,
                children: [{
                    text: 'Folder 1',
                    children: [{
                        text: 'Child 1',
                        canDropOnFirst: true,
                        canDropOnSecond: true,
                        leaf: true
                    }, {
                        text: 'Child 2',
                        canDropOnFirst: true,
                        canDropOnSecond: true,
                        leaf: true
                    }],
                    expanded: true
                }, {
                    text: 'Folder 2',
                    children: [],
                    expanded: true
                }]
            }
        });*/
        
        var tree_store = new Ext.data.TreeStore({
    		id:'tree_store',
    		proxy: {
    			type: 'ajax',
    			url: 'TreeData.sp',
    			reader: {
    				type: 'json'
    			}
    		},
    		root: { expanded: false },//
    		autoLoad: true
    	}); 
    
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
        var viewport = Ext.create('Ext.Viewport', {
            id: 'border-example',
            layout: 'border',
            items: [
            // create instance immediately
            Ext.create('Ext.Component', {
                region: 'north',
                height: 32, // give north and south regions a height
                autoEl: {
                    tag: 'div',
                    html:'<p>north - generally for menus, toolbars and/or advertisements</p>'
                }
            }), {
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
            }, {
                xtype: 'tabpanel',
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
                }, Ext.create('Ext.grid.PropertyGrid', {
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
            }, {
  //-----------------------------------------------------------
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
                items: [ {
                	/*
                	 * 第一种树结构，静态的
                	xtype:'treepanel',
                	rootVisible:false,
                	flex:1,
                    title: '用户管理',
                    store: store2,
                    iconCls:'nav',
                    viewConfig: {
                        plugins: {
                           ptype: 'treeviewdragdrop',
                           enableDrag: false,
                           enableDrop: true,
                           appendOnly: true
                        },
                        listeners: {
                            nodedragover: function(targetNode, position, dragData){
                                var rec = dragData.records[0],
                                    isFirst = targetNode.isFirst(),
                                    canDropFirst = rec.get('canDropOnFirst'),
                                    canDropSecond = rec.get('canDropOnSecond');
                                    
                                return isFirst ? canDropFirst : canDropSecond;
                            }
                        }
                    }*/
                	//第二种树结构，动态的
                	xtype:'treepanel',
                	id:"treePanel",
                	rootVisible:false,
                	iconCls:'nav',
                	flex:1,
                    title: '用户管理',
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
            			/*click: {
            	            element: 'el', //bind to the underlying el property on the panel
            	            fn: function(){ 
                					console.log('click el'); }
            	        }*/
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
            					
            					Ext.Msg.alert('设备管理', nodeId + nodeText);
            				}
            				//Ext.Msg.alert('text', nodeText);

            			}
            		}
                },{
                    title: '其他管理',
                    html: '<p>Some settings in here.</p>',
                    //contentEl:'west1',
                    iconCls: 'settings'
                }]
                
            },
            // in this instance the TabPanel is not wrapped by another panel
            // since no title is needed, this Panel is added directly
            // as a Container
           contentPanel]
        });
        
        
        // get a reference to the HTML element with id "hideit" and add a click listener to it
        Ext.get("hideit").on('click', function(){
            // get a reference to the Panel that was created with id = 'west-panel'
            var w = Ext.getCmp('west-panel');
            // expand or collapse that Panel based on its collapsed property state
            w.collapsed ? w.expand() : w.collapse();
        });
        
    });