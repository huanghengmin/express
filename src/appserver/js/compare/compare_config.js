Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var record = new Ext.data.Record.create([
        {name: 'compare_days', mapping: 'compare_days'},
        {name: 'ip', mapping: 'ip'},
        {name: 'port', mapping: 'port'},
        {name: 'compare_minutes', mapping: 'compare_minutes'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url: "../../CompareConfigAction_find.action"
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: "total",
        root: "rows"
    }, record);

    var store = new Ext.data.GroupingStore({
        id: "store.info",
        proxy: proxy,
        reader: reader
    });

    store.load();
    store.on('load', function () {
        var compare_days = store.getAt(0).get('compare_days');
        var compare_minutes = store.getAt(0).get('compare_minutes');
        var ip = store.getAt(0).get('ip');
        var port = store.getAt(0).get('port');
        Ext.getCmp('compare.compare_days').setValue(compare_days);
        Ext.getCmp('compare.compare_minutes').setValue(compare_minutes);
        Ext.getCmp('compare.ip').setValue(ip);
        Ext.getCmp('compare.port').setValue(port);
    });

    var formPanel = new Ext.form.FormPanel({
        plain: true,
        width: 500,
        labelAlign: 'right',
        labelWidth: 200,
        defaultType: 'textfield',
        defaults: {
            width: 250,
            allowBlank: false,
            blankText: '该项不能为空!'
        },
        items: [
            new Ext.form.NumberField({
                fieldLabel: '对比间隔(分钟)',
                name: 'compare_minutes',
                id: "compare.compare_minutes",
                allowBlank: false,
                allowDecimals: false,//是否允许输入小数(默认true)
                allowNegative: false,//是否允许输入负数(默认true)
                decimalPrecision: 0,//输入数字精度(默认保留小数点后2位)
                maxValue: 60,      //允许输入最大数值
                maxText: '最长间隔为60分种对比一次',   //最大值验证失败错误提示信息
                minValue: 1,//允许输入最小值
                minText: '最短间隔为1分种对比一次',//最小值验证失败错误提示信息
                nanText: '输入格式错误'        //无效数据错误提示信息
            }),
            new Ext.form.NumberField({
                fieldLabel: '重置间隔(天)',
                name: 'compare_days',
                id: "compare.compare_days",
                allowBlank: false,
                allowDecimals: false,//是否允许输入小数(默认true)
                allowNegative: false,//是否允许输入负数(默认true)
                decimalPrecision: 0,//输入数字精度(默认保留小数点后2位)
                maxValue: 30,      //允许输入最大数值
                maxText: '最长间隔为30天重置对比数据',   //最大值验证失败错误提示信息
                minValue: 1,//允许输入最小值
                minText: '最短间隔为1天重置对比数据',//最小值验证失败错误提示信息
                nanText: '输入格式错误'        //无效数据错误提示信息
            }),
            new Ext.form.TextField({
                fieldLabel: '对比服务器地址',
                name: 'ip',
                regex: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText: '请输入正确的IP地址',
                id: "compare.ip",
                allowBlank: false,
                blankText: "VPN服务器地址"
            }),
            new Ext.form.TextField({
                fieldLabel: '对比服务器端口',
                name: 'port',
                id: "compare.port",
                regex: /^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText: '请输入正确的端口',
                allowBlank: false,
                blankText: "VPN认证服务器端口"
            })
        ],
        buttons: [
            '->',
            {
                id: 'insert_win.info',
                text: '保存配置',
                handler: function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url: "../../CompareConfigAction_save.action",
                            method: 'POST',
                            waitTitle: '系统提示',
                            waitMsg: '正在连接...',
                            success: function () {
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: '保存成功,点击返回页面!',
                                    buttons: Ext.MessageBox.OK,
                                    buttons: {'ok': '确定'},
                                    icon: Ext.MessageBox.INFO,
                                    closable: false
                                });
                            },
                            failure: function () {
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: '保存失败，请与管理员联系!',
                                    buttons: Ext.MessageBox.OK,
                                    buttons: {'ok': '确定'},
                                    icon: Ext.MessageBox.ERROR,
                                    closable: false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title: '信息',
                            width: 200,
                            msg: '请填写完成再提交!',
                            buttons: Ext.MessageBox.OK,
                            buttons: {'ok': '确定'},
                            icon: Ext.MessageBox.ERROR,
                            closable: false
                        });
                    }
                }
            }
        ]
    });

    var panel = new Ext.Panel({
        plain: true,
        width: 600,
        border: false,
        items: [{
            id: 'panel.info',
            xtype: 'fieldset',
            title: '对比配置',
            width: 530,
            items: [formPanel]
        }]
    });
    new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        autoScroll: true,
        items: [{
            frame: true,
            autoScroll: true,
            items: [panel]
        }]
    });
});


