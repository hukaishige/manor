Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'WS',

    appFolder: 'app',

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'vbox',
            items: [
                {
                    xtype: 'form',
                    title: 'Wechat Service',
                    items: [{
                        xtype: 'filefield',
                        width:300,
                        name: 'media',
                        fieldLabel: 'Media',
                        labelWidth: 50,
                        msgTarget: 'side',
                        allowBlank: false,
                        anchor: '100%',
                        buttonText: 'Select'
                    },{
                    	xtype: 'textfield',
                    	id:'token',
                        fieldLabel: 'Token',
                        name: 'access_token',
                        value:''
                    },{
                    	xtype: 'textfield',
                        fieldLabel: 'Type',
                        name: 'type',
                        value:'image'
                    }],

                    buttons: [{
                        text: 'Upload',
                        handler: function() {
                            var form = this.up('form').getForm();
                            if(form.isValid()){
                                form.submit({
                                    url: '/analytic/admin/upload',
                                    waitMsg: 'Uploading your media...',
                                    success: function(fp, o) {
                                        Ext.Msg.alert('Success', 'Your media "' + o.result.file + '" has been uploaded.');
                                    }
                                });
                            }
                        }
                    }]
                }
            ]
        });
        
    	Ext.Ajax.request({
    	    url: '/analytic/admin/accessToken',
    	    success: function(response){
    	        var text = response.responseText;
    	        Ext.getCmp('token').setValue(text);
    	    }
    	});
    }
});