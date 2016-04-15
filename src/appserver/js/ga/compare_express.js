Ext.onReady(function () {

    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var company_store = new Ext.data.Store({
        autoLoad: true,
        reader: new Ext.data.JsonReader({
            fields: ["id", "code", "name"],
            totalProperty: 'totalCount',
            root: 'root'
        })
    });

    var start = 0;
    var pageSize = 15;
    var toolbar = new Ext.Toolbar({
        plain: true,
        width: 350,
        height: 30,
        items: ['起始日期：', {
            id: 'startDate.tb.info',
            xtype: 'datefield',
            name: 'startDate',
            emptyText: '点击输入日期',
            format: 'Y-m-d'
        }, {
            xtype: 'tbspacer',
            width: 10
        }, '结束日期：', {
            id: 'endDate.tb.info',
            xtype: 'datefield',
            name: 'endDate',
            emptyText: '点击输入日期',
            format: 'Y-m-d'
        }, /*'寄件人', {
         id: 'name.tb.info',
         xtype: 'textfield',
         emptyText: '输入寄件人姓名',
         width: 100
         }, {
         xtype: 'tbspacer',
         width: 10
         },*/ '寄件人身份证', {
            id: 'idCard.tb.info',
            xtype: 'textfield',
            emptyText: '输入寄件人身份证',
            width: 100
        }, {
            xtype: 'tbspacer',
            width: 10
        }, '快递单号', {
            id: 'shapeCode.tb.info',
            xtype: 'textfield',
            emptyText: '输入条快递单号',
            width: 100
        }, /* {
         xtype: 'tbspacer',
         width: 10
         }, '快递员电话', {
         id: 'phone.tb.info',
         xtype: 'textfield',
         emptyText: '输入快递员电话',
         width: 100
         },*/ {
            xtype: 'tbspacer',
            width: 10
        }, '快递公司', {
            id: 'express_code.tb.info',
            xtype: 'combo',
            mode: 'remote',// 指定数据加载方式，如果直接从客户端加载则为local，如果从服务器断加载// 则为remote.默认值为：remote
            border: true,
            frame: true,
            //pageSize: 10,// 当元素加载的时候，如果返回的数据为多页，则会在下拉列表框下面显示一个分页工具栏，该属性指定每页的大小
            // 在点击分页导航按钮时，将会作为start及limit参数传递给服务端，默认值为0，只有在mode='remote'的时候才能够使用
            editable: true,
            //fieldLabel: '快递公司',
            emptyText: '请选择快递公司',
            //hiddenName : 'user.company.code',
            triggerAction: "all",// 是否开启自动查询功能
            store: company_store,// 定义数据源
            valueField: "code", // 关联某一个逻辑列名作为显示值
            displayField: "name", // 关联某一个逻辑列名作为显示值
            //mode : "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
            //name: 'user.company.name',
            allowBlank: false,
            blankText: "请选择快递公司",
            listeners: {
                focus: function (e) {
                    company_store.load();
                },
                /*render: function () {
                 company_store.proxy = new Ext.data.HttpProxy({
                 url: '../../CompanyAction_findCompany.action',
                 method: "POST"
                 })
                 }*/
                beforequery: function (e) {
                    var combo = e.combo;
                    if (!e.forceAll) {
                        var value = e.query;
                        combo.store.filterBy(function (record, id) {
                            var text = record.get(combo.displayField);
                            return (text.indexOf(value) != -1);
                        });
                        combo.expand();
                        return false;
                    }
                },
                render: function () {
                    company_store.proxy = new Ext.data.HttpProxy({
                        url: '../../CompanyAction_findAll.action',
                        method: "POST"
                    })
                }
            }
        }, /* '快递公司',{
         id:'express_company.tb.info',
         xtype:'textfield',
         emptyText :'输入快递公司',
         width : 100
         },{
         xtype : 'tbspacer',
         width : 10
         }, '快递员',{
         id:'express.tb.info',
         xtype:'textfield',
         emptyText :'输入快递员',
         width : 100
         },{
         xtype : 'tbspacer',
         width : 10
         }, '快递员编号',{
         id:'express_number.tb.info',
         xtype:'textfield',
         emptyText :'输入快递员编号',
         width : 100
         },*/ {
            text: '查询',
            iconCls: 'select',
            listeners: {
                click: function () {
                    var startDate = Ext.fly("startDate.tb.info").dom.value == '点击输入日期'
                        ? null
                        : Ext.fly('startDate.tb.info').dom.value;

                    var endDate = Ext.fly('endDate.tb.info').dom.value == '点击输入日期'
                        ? null
                        : Ext.fly('endDate.tb.info').dom.value;

                    /*   var name = Ext.fly("name.tb.info").dom.value == '输入寄件人姓名'
                     ? null
                     : Ext.getCmp('name.tb.info').getValue();*/

                    var idCard = Ext.fly("idCard.tb.info").dom.value == '输入寄件人身份证'
                        ? null
                        : Ext.getCmp('idCard.tb.info').getValue();

                    var shapeCode = Ext.fly("shapeCode.tb.info").dom.value == '输入寄件编号'
                        ? null
                        : Ext.getCmp('shapeCode.tb.info').getValue();

                    /*  var phone = Ext.fly("phone.tb.info").dom.value == '输入快递员电话'
                     ? null
                     : Ext.getCmp('phone.tb.info').getValue();
                     */
                    var express_code = Ext.fly("express_code.tb.info").dom.value == '输入快递员电话'
                        ? null
                        : Ext.getCmp('express_code.tb.info').getValue();


                    /* var express = Ext.fly("express.tb.info").dom.value == '输入快递员'
                     ? null
                     : Ext.getCmp('express.tb.info').getValue();

                     var express_number = Ext.fly("express_number.tb.info").dom.value == '输入快递员编号'
                     ? null
                     : Ext.getCmp('express_number.tb.info').getValue();

                     var express_company = Ext.fly("express_company.tb.info").dom.value == '输入公司'
                     ? null
                     : Ext.getCmp('express_company.tb.info').getValue();*/

                    store.setBaseParam('startDate', startDate);
                    store.setBaseParam('endDate', endDate);
                    //store.setBaseParam('name', name);
                    store.setBaseParam('idCard', idCard);
                    store.setBaseParam('shapeCode', shapeCode);
                    //store.setBaseParam('phone', phone);
                    store.setBaseParam('express_code', express_code);
                    /*store.setBaseParam('express', express);
                     store.setBaseParam('express_number', express_number);
                     store.setBaseParam('express_company', express_company);*/
                    store.load({
                        params: {
                            start: start,
                            limit: pageSize
                        }
                    });
                }
            }
        }/*,{
         text: '高级查询',
         iconCls: 'select',
         listeners: {
         click: function () {
         var formPanel = new Ext.form.FormPanel({
         frame: true,
         autoScroll: true,
         labelWidth: 150,
         labelAlign: 'right',
         defaultWidth: 300,
         autoWidth: true,
         layout: 'form',
         border: false,
         defaults: {
         anchor: '90%',
         allowBlank: false,
         xtype: 'textfield',
         blankText: '该项不能为空！'
         },
         items:[{
         fieldLabel:'起始日期',
         id: 'gj.startDate.tb.info',
         xtype: 'datefield',
         name: 'startDate',
         emptyText: '点击输入日期',
         format: 'Y-m-d'
         }, {
         fieldLabel:'结束日期',
         id: 'gj.endDate.tb.info',
         xtype: 'datefield',
         name: 'endDate',
         emptyText: '点击输入日期',
         format: 'Y-m-d'
         },{
         fieldLabel:'寄件人',
         id: 'gj.name.tb.info',
         xtype: 'textfield',
         emptyText: '输入寄件人姓名'
         },  {
         fieldLabel:'寄件人身份证',
         id: 'gj.idCard.tb.info',
         xtype: 'textfield',
         emptyText: '输入寄件人身份证'
         }, {
         fieldLabel:'快递单号',
         id: 'gj.shapeCode.tb.info',
         xtype: 'textfield',
         emptyText: '输入条快递单号'
         }, {
         fieldLabel:'快递员电话',
         id: 'gj.phone.tb.info',
         xtype: 'textfield',
         emptyText: '输入快递员电话'
         },  {
         id: 'gj.express_code.tb.info',
         fieldLabel:'快递公司',
         xtype: 'combo',
         mode: 'remote',// 指定数据加载方式，如果直接从客户端加载则为local，如果从服务器断加载// 则为remote.默认值为：remote
         border: true,
         frame: true,
         //pageSize: 10,// 当元素加载的时候，如果返回的数据为多页，则会在下拉列表框下面显示一个分页工具栏，该属性指定每页的大小
         // 在点击分页导航按钮时，将会作为start及limit参数传递给服务端，默认值为0，只有在mode='remote'的时候才能够使用
         editable: true,
         //fieldLabel: '快递公司',
         emptyText: '请选择快递公司',
         //hiddenName : 'user.company.code',
         triggerAction: "all",// 是否开启自动查询功能
         store: company_store,// 定义数据源
         valueField: "code", // 关联某一个逻辑列名作为显示值
         displayField: "name", // 关联某一个逻辑列名作为显示值
         //mode : "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
         //name: 'user.company.name',
         allowBlank: false,
         blankText: "请选择快递公司",
         listeners: {
         /!*render: function () {
         company_store.proxy = new Ext.data.HttpProxy({
         url: '../../CompanyAction_findCompany.action',
         method: "POST"
         })
         }*!/
         beforequery: function (e) {
         var combo = e.combo;
         if (!e.forceAll) {
         var value = e.query;
         combo.store.filterBy(function (record, id) {
         var text = record.get(combo.displayField);
         return (text.indexOf(value) != -1);
         });
         combo.expand();
         return false;
         }
         },
         render: function () {
         company_store.proxy = new Ext.data.HttpProxy({
         url: '../../CompanyAction_findAll.action',
         method: "POST"
         })
         }
         }
         }]
         });
         var win = new Ext.Window({
         title:"查询信息",
         width:650,
         layout:'fit',
         height:390,
         modal:true,
         items:formPanel,
         bbar:[
         '->',
         {
         text:'查询',
         handler:function(){
         var startDate = Ext.getCmp("gj.startDate.tb.info").getValue();
         var endDate = Ext.getCmp("gj.endDate.tb.info").getValue();
         var name = Ext.getCmp("gj.name.tb.info").getValue();
         var idCard = Ext.getCmp("gj.idCard.tb.info").getValue();
         var shapeCode = Ext.getCmp("gj.shapeCode.tb.info").getValue();
         var phone = Ext.getCmp("gj.phone.tb.info").getValue();
         var express_code = Ext.getCmp("gj.express_code.tb.info").getValue();
         store.setBaseParam('startDate', startDate);
         store.setBaseParam('endDate', endDate);
         store.setBaseParam('name', name);
         store.setBaseParam('idCard', idCard);
         store.setBaseParam('shapeCode', shapeCode);
         store.setBaseParam('phone', phone);
         store.setBaseParam('express_code', express_code);


         }
         },{
         text:'关闭',
         handler:function(){
         win.close();
         }
         }
         ]
         }).show();

         }
         }
         }*/]
    });
    var record = new Ext.data.Record.create([
        {name: 'id', mapping: 'id'},
        {name: 'name', mapping: 'name'},
        {name: 'sex', mapping: 'sex'},
        {name: 'nation', mapping: 'nation'},
        {name: 'birthday', mapping: 'birthday'},
        {name: 'address', mapping: 'address'},
        {name: 'idCard', mapping: 'idCard'},
        {name: 'signDepart', mapping: 'signDepart'},
        {name: 'validTime', mapping: 'validTime'},
        {name: 'shapeCode', mapping: 'shapeCode'},
        {name: 'DN', mapping: 'DN'},
        {name: 'longitude', mapping: 'longitude'},
        {name: 'latitude', mapping: 'latitude'},
        {name: 'phone', mapping: 'phone'},
        {name: 'contact', mapping: 'contact'},
        {name: 'status', mapping: 'status'},
        {name: 'express_idCard', mapping: 'express_idCard'},
        {name: 'express', mapping: 'express'},
        {name: 'express_company', mapping: 'express_company'},
        {name: 'express_number', mapping: 'express_number'},
        {name: 'sendTime', mapping: 'sendTime'},
        {name: 'status', mapping: 'status'},
        {name: 'code', mapping: 'code'},
        {name: 'compare_time', mapping: 'compare_time'},
        {name: 'xq', mapping: 'xq'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url: "../../ExpressRealNameAction_findByGAAccount.action"
    });
    var reader = new Ext.data.JsonReader({
        totalProperty: "total",
        root: "rows",
        id: 'id'
    }, record);

    var store = new Ext.data.GroupingStore({
        id: "store.info",
        proxy: proxy,
        reader: reader
    });

    store.load({
        params: {
            start: start, limit: pageSize
        }
    });
    //var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
        //boxM,
        rowNumber,
        {header: "寄件人", dataIndex: "name", width: 50, align: 'center', sortable: true, menuDisabled: true},
        {header: "寄件人身份证", dataIndex: "idCard", align: 'center', sortable: true, menuDisabled: true},
        {header: "快递公司", dataIndex: "express_company", align: 'center', sortable: true, menuDisabled: true},
        {header: "快递员", dataIndex: "express", align: 'center', sortable: true, menuDisabled: true},
        {header: "快递员身份证", dataIndex: "express_idCard", align: 'center', sortable: true, menuDisabled: true},
        {header: "快递员编号", dataIndex: "express_number", align: 'center', sortable: true, menuDisabled: true},
        {header: "快递单号", dataIndex: "shapeCode", align: 'center', sortable: true, menuDisabled: true},
        {header: "寄件时间", dataIndex: "sendTime", align: 'center', sortable: true, menuDisabled: true},
        {header: "寄件人电话", dataIndex: "contact", align: 'center', sortable: true, menuDisabled: true},
        {
            header: '状态',
            dataIndex: "status",
            align: 'center',
            sortable: true,
            menuDisabled: true,
            renderer: show_status,
            width: 100
        }, {
            header: '对比结果',
            dataIndex: "code",
            align: 'center',
            sortable: true,
            menuDisabled: true,
            renderer: show_code,
            width: 100
        }, {
            header: '操作标记',
            dataIndex: "flag",
            align: 'center',
            sortable: true,
            menuDisabled: true,
            renderer: show_flag,
            width: 100
        }
    ]);
    var page_toolbar = new Ext.PagingToolbar({
        pageSize: pageSize,
        store: store,
        displayInfo: true,
        displayMsg: "显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg: "没有记录",
        beforePageText: "当前页",
        afterPageText: "共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id: 'grid.info',
        plain: true,
        viewConfig: {
            forceFit: true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
        },
        bodyStyle: 'width:100%',
        loadMask: {msg: '正在加载数据，请稍后...'},
        cm: colM,
        //sm: boxM,
        store: store,
        tbar: toolbar,
        bbar: page_toolbar,
        //title: '资源配置',
        columnLines: true,
        autoScroll: true,
        border: false,
        collapsible: false,
        stripeRows: true,
        autoExpandColumn: 'Position',
        enableHdMenu: true,
        enableColumnHide: true,
        selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
        height: 300,
        frame: true,
        iconCls: 'icon-grid'
    });

    var port = new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        items: [grid_panel]
    });

});

function show_status(value) {
    if (value == "1") {
        return '<span style="color:green;">已对比</span>'
    } else if (value == "0") {
        return '<span style="color:red;">未对比</span>'
    }
}

function show_code(value) {
    if (value == "000") {
        return '<span style="color:green;">正常</span>'
    } else if (value == "001") {
        return '<span style="color:red;">存疑</span>'
    } else if (value == "002") {
        return '<span style="color:red;">抓捕</span>'
    } else if (value == "099") {
        return '<span style="color:green;">查询失败</span>'
    }else{
        return '<span style="color:red;">未对比</span>'
    }
}

function show_flag() {
    return String.format(
        '<a id="delete_express.info" href="javascript:void(0);" onclick="delete_rule();return false;" style="color: green;">删除</a>&nbsp;&nbsp;&nbsp;' +
        '<a id="info_express.info" href="javascript:void(0);" onclick="viewInfo();return false;" style="color: green;">详细</a>&nbsp;&nbsp;&nbsp;'
    );
}

function delete_rule() {
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    if (!recode) {
        Ext.Msg.alert("提示", "请选择一条记录!");
    } else {
        Ext.Msg.confirm("提示", "确定删除这条记录？", function (sid) {
            if (sid == "yes") {
                Ext.Ajax.request({
                    url: "../../ExpressRealNameAction_remove.action",
                    timeout: 20 * 60 * 1000,
                    method: "POST",
                    params: {
                        id: recode.get("id")
                    },
                    success: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                        grid_panel.getStore().reload();
                    },
                    failure: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                    }
                });
            }
        });
    }
}

function clickHandler(url) {
    var win = new Ext.Window({
        width: 650,
        layout: 'fit',
        height: 450,
        modal: true,
        maximizable: true,
        minimizable: true,
        items: [
            {
                xtype: 'box',//或者xtype: 'component',
                width: 650, //图片宽度
//                height: 300, //图片高度
                autoEl: {
                    tag: 'img',    //指定为img标签
                    src: url   //指定url路径
                }
            }
        ]
    }).show();
}


function viewInfo() {
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    var status_value = null;
    var code_value = null;
    var status = recode.get("status");
    var code = recode.get("code");
    if (status == "1") {
        status_value = '<span style="color:green;">已对比</span>'
    } else if (status == "0") {
        status_value = '<span style="color:red;">未对比</span>'
    }

    if (code == "000") {
        code_value = '<span style="color:green;">正常</span>'
    } else if (code == "001") {
        code_value = '<span style="color:red;">存疑</span>'
    } else if (code == "002") {
        code_value = '<span style="color:red;">抓捕</span>'
    } else if (code == "099") {
        code_value = '<span style="color:green;">查询失败</span>'
    }else{
        code_value = '<span style="color:red;">未对比</span>'
    }

    var unpack_url = '../../ExpressRealNameAction_loadUnpackImg.action?id=' + recode.get("id");
    var sender_url = '../../ExpressRealNameAction_loadSenderImg.action?id=' + recode.get("id");
    var face_url = '../../ExpressRealNameAction_loadFaceImg.action?id=' + recode.get("id");

    var userPanel = new Ext.Panel({
        plain: true,
        layout: 'column',
        border: false,
        items: [{
            columnWidth: .7,
            plain: true,
            labelWidth: 180,
            xtype: 'form',
            border: false,
            loadMask: {msg: '正在加载数据，请稍后.....'},
            labelAlign: 'right',
            buttonAlign: 'left',
            defaultType: 'displayfield',
            defaults: {
                width: 200,
                allowBlank: false,
                blankText: '该项不能为空！'
            },
            items: [new Ext.form.DisplayField({
                fieldLabel: '寄件人',
                value: recode.get("name")
            }),
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人身份证',
                    value: recode.get("idCard")
                }),
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人状态',
                    value: status_value
                }),
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人性别',
                    value: recode.get("sex")
                }),
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人民族',
                    value: recode.get("nation")
                }),
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人出生日期',
                    value: recode.get("birthday")
                }),

                new Ext.form.DisplayField({
                    fieldLabel: '寄件人地址',
                    value: recode.get("address")
                }),
                /*  new Ext.form.DisplayField({
                 fieldLabel: '寄件人身份证DN码',
                 value: recode.get("DN")
                 }),*/
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人身份证签发机构',
                    value: recode.get("signDepart")
                }),
                new Ext.form.DisplayField({
                    fieldLabel: '寄件人身份证有效日期',
                    value: recode.get("validTime")
                }), new Ext.form.DisplayField({
                    fieldLabel: '寄件时间',
                    value: recode.get("sendTime")
                }), new Ext.form.DisplayField({
                    fieldLabel: '寄件人联系方式',
                    value: recode.get("contact")
                }),/* new Ext.form.DisplayField({
                    fieldLabel: '状态',
                    value: status_value
                }),*/ new Ext.form.DisplayField({
                    fieldLabel: '对比结果',
                    value: code_value
                }), new Ext.form.DisplayField({
                    fieldLabel: '对比详细',
                    value: recode.get("xq")
                }), new Ext.form.DisplayField({
                    fieldLabel: '对比时间',
                    value: recode.get("compare_time")
                })]
        }, {
            columnWidth: .2,
            xtype: 'box',
            width: 80,
            autoEl: {
                tag: 'img',    //指定为img标签
                src: '../../ExpressRealNameAction_loadBytes.action?id=' + recode.get("id")   //指定url路径
            }
        }, {
            columnWidth: .1
        }]

    });
    var userPanel2 = new Ext.form.FormPanel({
                plain: true,
                labelWidth: 180,
                border: false,
                loadMask: {msg: '正在加载数据，请稍后.....'},
                labelAlign: 'right',
                buttonAlign: 'left',
                defaultType: 'displayfield',
                defaults: {
                    width: 200,
                    allowBlank: false,
                    blankText: '该项不能为空！'
                },
                items: [
                    new Ext.form.DisplayField({
                        fieldLabel: '快递公司',
                        value: recode.get("express_company")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '快递员',
                        value: recode.get("express")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '快递员编号',
                        value: recode.get("express_number")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '快递单号',
                        value: recode.get("shapeCode")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '快递员身份证',
                        value: recode.get("express_idCard")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '快递员电话',
                        value: recode.get("phone")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '经度',
                        value: recode.get("longitude")
                    }),
                    new Ext.form.DisplayField({
                        fieldLabel: '纬度',
                        value: recode.get("latitude")
                    }),

                    {
                        xtype: 'box',//或者xtype: 'component',
                        fieldLabel: '寄件人照',
                        width: 350, //图片宽度
                        height: 200, //图片高度
                        autoEl: {
                            tag: 'img',    //指定为img标签
                            src: sender_url, //指定url路径
                            onclick: 'clickHandler(\'' + sender_url + '\')'
                        }
                    }
                    , {
                        xtype: 'box',//或者xtype: 'component',
                        fieldLabel: '开箱照',
                        width: 350, //图片宽度
                        height: 200, //图片高度
                        autoEl: {
                            tag: 'img',    //指定为img标签
                            src: unpack_url, //指定url路径
                            onclick: 'clickHandler(\'' + unpack_url + '\')'
                        }
                    }
                    ,
                    {
                        xtype: 'box',//或者xtype: 'component',
                        fieldLabel: '面单照',
                        width: 350, //图片宽度
                        height: 200, //图片高度
                        autoEl: {
                            tag: 'img',    //指定为img标签
                            src: face_url, //指定url路径
                            onclick: 'clickHandler(\'' + face_url + '\')'
                        }
                    }
                ]
            }
        )
        ;


    /*  var formPanel = new Ext.Panel({
     frame: true,
     autoScroll: true,
     baseCls: 'x-plain',
     labelWidth: 180,
     labelAlign: 'right',
     layout: 'form',
     border: false,
     defaults: {
     anchor:"95%"
     },
     items: [
     /!*   {
     xtype: 'box',//或者xtype: 'component',
     fieldLabel: '头像',
     width: 100, //图片宽度
     //height: 200, //图片高度
     autoEl: {
     tag: 'img',    //指定为img标签
     src: '../../ExpressRealNameAction_loadBytes.action?id=' + recode.get("id")   //指定url路径
     }
     },*!/



     ]
     });*/

    var select_Win = new Ext.Window({
        title: "寄件详细",
        width: 800,
        layout: 'form',
        autoScroll: true,
        height: 500,
        modal: true,
        items: [userPanel, userPanel2]
    });
    select_Win.show();
}

