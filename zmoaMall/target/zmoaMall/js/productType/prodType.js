/**
 * Created by Bochao on 2017/3/30.
 */

var step = 0;

function nextStep() {
    step++;
}

/**
 * 添加产品类型
 */
function addProdType() {
    //提交form表单
    $('#addProdType-fm').form('submit', {
        url:'/business/prodCat/addProdType.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addProdType-dlg').dialog('close');
                $('#type-dg').datagrid('reload');
                nextStep();
                addProdTypeAttrDlg(data.data);
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }

        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}


/**
 * 修改产品类型基本信息
 */
function updateProdType() {
    $('#updateProdType-fm').form('submit', {
        url:'/business/prodCat/updateProdType.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#updateProdType-dlg').dialog('close');
                $('#type-dg').datagrid('reload');
                jQuery.messager.show({title:'数据修改成功提示！', msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据', showType:'show'});
            }
        },
        error: function() {
            jQuery.messager.show({title:'请求失败！', msg: new Date().toLocaleString() + ' 请求服务器失败', showType:'show'});
        }
    });
}

/**
 * 显示修改产品类型对话框
 */
function updateProdTypeDlg() {
    var row = $('#type-dg').datagrid('getSelected');
    console.log(row);
    if (row) {
        $('#updateProdType-dlg').dialog({
            title: '修改产品类型',
            width: 700,
            height: 400,
            href: '/business/prodCat/updateProdTypeDlg?typeId='+row.typeId,
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:function() {
                    updateProdType();
                }
            },{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                    $('#updateProdType-dlg').dialog('close');
                }
            }]
        });
    } else {
        jQuery.messager.show({
            title:'提示！',
            msg:'请先选中一条记录！',
            showType:'show',
            style:{
                left:'',
                right:0,
                top:document.body.scrollTop+document.documentElement.scrollTop,
                bottom:''
            }
        });
    }
}

/**
 * 显示添加产品类型对话框
 */
function addProdTypeDlg() {
    step = 0;
    $('#addProdType-dlg').dialog({
        title: '新增产品类型',
        width: 700,
        height: 400,
        href: '/business/prodCat/addProdTypeDlg',
        buttons:[{
            text:'下一步',
            iconCls:'icon-ok',
            handler:function() {

                addProdType();
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                step = 0;
                $('#addProdType-dlg').dialog('close');
            }
        }]
    });
    //$('#add-fm').form('clear');
}

/**
 * 添加产品类型属性
 */
function addProdTypeAttr(typeId) {

    $('#addProdTypeAttr-dlg').dialog('close');
    nextStep();
    addProdTypeSpecDlg(typeId);

    /*//提交form表单
     $('#addProdTypeAttr-fm').form('submit', {
     url:'/business/prodCat/addProdTypeAttr',
     success: function(data) {
     var data = eval('(' + data + ')');  // change the JSON string to javascript object
     if (data.success) {
     $('#addProdTypeAttr-dlg').dialog('close');
     $('#type-dg').datagrid('reload');

     jQuery.messager.show({
     title:'数据修改成功提示！',
     msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
     showType:'show'
     });
     }

     },
     error: function() {
     jQuery.messager.show({
     title:'请求失败！',
     msg: new Date().toLocaleString() + ' 请求服务器失败',
     showType:'show'
     });
     }
     });*/

}

function addProdTypeAttrDlg(typeId) {
    $('#addProdTypeAttr-dlg').dialog({
        title: '新增产品类型属性',
        width: 700,
        height: 400,
        href: '/business/prodCat/addProdTypeAttrDlg?typeId='+typeId,
        buttons:[{
            text:'下一步',
            iconCls:'icon-ok',
            handler:function() {
                addProdTypeAttr(typeId);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                step = 0;
                $('#addProdTypeAttr-dlg').dialog('close');
            }
        }]
    });
    //$('#add-fm').form('clear');
}

function addProdTypeSpec() {
    //提交form表单
    $('#addProdType-fm').form('submit', {
        url:'/business/prodCat/addProdTypeSpec.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addProdTypeSpec-dlg').dialog('close');
                $('#type-dg').datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }

        },
        error: function() {
            alert("error")
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });

}

function addProdTypeSpecDlg(typeId) {

    $('#addProdTypeSpec-dlg').dialog({
        title: '新增产品类型规格',
        width: 700,
        height: 400,
        href: '/business/prodCat/addProdTypeSpecDlg?typeId='+typeId,
        buttons:[{
            text:'完成',
            iconCls:'icon-ok',
            handler:function() {
                step = 0;
                $('#addProdTypeSpec-dlg').dialog('close');
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                step = 0;
                $('#addProdTypeSpec-dlg').dialog('close');
            }
        }]
    });
    //$('#add-fm').form('clear');

}


function updateStatus(status) {
    var row = $('#type-dg').datagrid('getSelected');
    if (row) {
        jQuery.ajax({
            url: '/business/ad/updateAdStatus',
            type: 'post',
            data: 'id='+row.id+'&status='+status,
            dataType: 'json',
            success: function(data) {
                if (data.success) {
                    $('#type-dg').datagrid('reload');
                    jQuery.messager.show({
                        title:'数据修改成功提示！',
                        msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                        showType:'show'
                    });
                }
            }
        });
    } else {
        //TODO 操作提示
    }
}

//筛选开关
var i = 1;
function searchCode() {
    if (i == 1) {
        $("#type-dg").datagrid('enableFilter');
        i = 0;
    } else {
        $("#type-dg").datagrid('disableFilter');
        i = 1;
    }
}


function addProdTypeInputAttr() {
    $('#attr-add-fm').form('submit', {
        url:'/business/prodCat/addProdTypeAttr.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addProdTypeInputAttr-dlg').dialog('close');
                $('#attr-dg').datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }
        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

function addProdTypeInputAttrDlg(typeId, usageMode) {
    $('#addProdTypeInputAttr-dlg').dialog({
        title: '修改类型属性',
        width: 500,
        height: 300,
        modal: true,
        href: '/business/prodCat/addProdTypeInputAttrDlg?typeId=' + typeId + '&usageMode=' + usageMode,
        buttons:[{
            text:'添加',
            iconCls:'icon-ok',
            handler:function() {
                addProdTypeInputAttr();
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function() {
                $('#addProdTypeInputAttr-dlg').dialog('close');
            }
        }]
    });
}


/**
 * 属性值和规格值空值格式化显示
 */
function valueStrFormatter(value, row, index) {
    if (value == '' && row.usageMode != 3) {
        return '<<直接填写>>';
    } else if (value == '' && row.usageMode == 3) {
        return '<<无>>';
    } else {
        return value;
    }
}


/**
 * 修改类型属性
 */
function updateProdTypeAttr() {
    $('#updateProdTypeAttr-fm').form('submit', {
        url:'/business/prodCat/updateProdTypeAttr.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#updateProdTypeAttr-dlg').dialog('close');
                $('#attr-dg').datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }
        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

/**
 * 修改类型属性对话框
 */
function updateProdTypeAttrDlg() {
    var row = $('#attr-dg').datagrid('getSelected');
    console.log(row);
    if (row) {
        $('#updateProdTypeAttr-dlg').dialog({
            title: '修改类型属性',
            width: 500,
            height: 300,
            modal: true,
            href: '/business/prodCat/updateProdTypeAttrDlg?attributeId=' + row.attributeId,
            buttons:[{
                text:'确定',
                iconCls:'icon-ok',
                handler:function() {
                    updateProdTypeAttr();
                }
            },{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function() {
                    $('#updateProdTypeAttr-dlg').dialog('close');
                }
            }]
        });

    } else {
        jQuery.messager.show({
            title:'提示！', msg:'请先选中一条记录！', showType:'show',
            style:{left:'', right:0, top:document.body.scrollTop+document.documentElement.scrollTop, bottom:''}
        });
    }
}

/**
 * 修改类型规格
 */
function updateProdTypeSpec(specDg) {
    $('#updateProdTypeSpec-fm').form('submit', {
        url:'/business/prodCat/updateProdTypeSpec.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#updateProdTypeSpec-dlg').dialog('close');
                specDg.datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }
        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

/**
 * 修改类型规格对话框
 */
function updateProdTypeSpecDlg(specDg) {
    var row = specDg.datagrid('getSelected');
    console.log(row);
    if (row) {
        $('#updateProdTypeSpec-dlg').dialog({
            title: '修改类型属性',
            width: 500,
            height: 300,
            modal: true,
            href: '/business/prodCat/updateProdTypeSpecDlg?attributeId=' + row.attributeId,
            buttons:[{
                text:'确定',
                iconCls:'icon-ok',
                handler:function() {
                    updateProdTypeSpec(specDg);
                }
            },{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function() {
                    $('#updateProdTypeSpec-dlg').dialog('close');
                }
            }]
        });

    } else {
        jQuery.messager.show({
            title:'提示！', msg:'请先选中一条记录！', showType:'show',
            style:{left:'', right:0, top:document.body.scrollTop+document.documentElement.scrollTop, bottom:''}
        });
    }
}



/**
 * 规格类型人性化显示
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function specTypeFormatter(value, row, index) {
    if (value == 0) {
        return '文字';
    } else if (value == 1) {
        return '图片';
    }
}

/**
 * 文字规格添加对话框
 */
function addCharSpecDlg(specDg, typeId) {
    $('#addProdTypeCharSpec-dlg').dialog({
        title: '新增文字规格',
        width: 400,
        height: 300,
        modal: true,
        href: '/business/prodCat/addProdTypeCharSpecDlg?typeId=' + typeId,
        buttons:[{
            text:'确定',
            iconCls:'icon-ok',
            handler:function() {
                addCharSpec(specDg);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function() {
                $('#addProdTypeCharSpec-dlg').dialog('close');
            }
        }]
    });
}

/**
 * 文字规格添加
 */
function addCharSpec(specDg) {
    //提交form表单
    $('#spec-char-fm').form('submit', {
        url:'/business/prodCat/addProdTypeSpec.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addProdTypeCharSpec-dlg').dialog('close');
                specDg.datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }
        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

/**
 * 图片规格添加对话框
 */
function addPicSpecDlg(specDg, typeId) {
    $('#addProdTypePicSpec-dlg').dialog({
        title: '新增图片规格',
        width: 400,
        height: 250,
        modal: true,
        href: '/business/prodCat/addProdTypePicSpecDlg?typeId=' + typeId,
        buttons:[{
            text:'确定',
            iconCls:'icon-ok',
            handler:function() {
                addPicSpec(specDg);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $('#addProdTypePicSpec-dlg').dialog('close');
            }
        }]
    });
}

/**
 * 图片规格添加
 */
function addPicSpec(specDg) {
    //提交form表单
    $('#spec-pic-fm').form('submit', {
        url:'/business/prodCat/addProdTypeSpec.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addProdTypePicSpec-dlg').dialog('close');
                specDg.datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }

        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

/**
 * 判断选中记录是文字属性还是图片属性，显示对应的添加对话框
 */
function addSpecValue(specDg) {
    var row = specDg.datagrid('getSelected');
    console.log(row);
    if (row) {
        if (row.useAttributeImage == 1) {
            addSpecPicDlg(specDg, row.attributeId);
        } else if (row.useAttributeImage == 0) {
            addSpecCharDlg(specDg, row.attributeId);
        }

    } else {
        jQuery.messager.show({
            title:'提示！',
            msg:'请先选中一条记录！',
            showType:'show',
            style:{
                left:'',
                right:0,
                top:document.body.scrollTop+document.documentElement.scrollTop,
                bottom:''
            }
        });
    }

}


/**
 * 图片规格添加图片
 */
function addSpecPic(specDg) {
    $('#spec-pic-add-fm').form('submit', {
        url:'/business/prodCat/addPicSpecValue.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addSpecPic-dlg').dialog('close');
                specDg.datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }

        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

/**
 * 图片规格添加图片对话框
 * @param attributeId
 */
function addSpecPicDlg(specDg, attributeId) {
    $('#addSpecPic-dlg').dialog({
        title: '新增图片规格值',
        width: 400,
        height: 250,
        modal: true,
        href: '/business/prodCat/addSpecPicDlg?attributeId=' + attributeId,
        buttons:[{
            text:'确定',
            iconCls:'icon-ok',
            handler:function() {
                addSpecPic(specDg);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $('#addSpecPic-dlg').dialog('close');
            }
        }]
    });
}


/**
 * 文字规格添加文字
 */
function addSpecChar(specDg) {
    $('#spec-char-add-fm').form('submit', {
        url:'/business/prodCat/addCharSpecValue.action',
        success: function(data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addSpecChar-dlg').dialog('close');
                specDg.datagrid('reload');
                jQuery.messager.show({
                    title:'数据修改成功提示！',
                    msg: '您在 ' + new Date().toLocaleString() + ' 成功更新了一条数据',
                    showType:'show'
                });
            }
        },
        error: function() {
            jQuery.messager.show({
                title:'请求失败！',
                msg: new Date().toLocaleString() + ' 请求服务器失败',
                showType:'show'
            });
        }
    });
}

/**
 * 文字规格添加文字对话框
 * @param attributeId
 */
function addSpecCharDlg(specDg, attributeId) {
    $('#addSpecChar-dlg').dialog({
        title: '新增文字规格值',
        width: 400,
        height: 250,
        modal: true,
        href: '/business/prodCat/addSpecCharDlg?attributeId=' + attributeId,
        buttons:[{
            text:'确定',
            iconCls:'icon-ok',
            handler:function() {
                addSpecChar(specDg);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $('#addSpecPic-dlg').dialog('close');
            }
        }]
    });
}
