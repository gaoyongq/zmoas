/**
 * 基于VUE实现的js生成table
 * 提交信息：
 * {page:当前页,pageSize:每页条数,sort:排序方式ASC和DESC两种，不区分大小写,diy:其他的一些信息}
 * 列表返回信息：
 * {page:当前页,total:总记录,columns:[{field:属性名,name:显示名}],rows:[{field:属性名,value:属性值}]}
 * 响应数据模型：{success:true,message:null,obj:{page:1,total:50,columns:[{field:'AA',name:'名字',hidden:true,isId:true}],rows:[{AA:'值1'}]}}
 *<div>
 </div>
 */
var gloabBean = {};
jQuery.extend(jQuery.fn, {
    /**
     * options:{pageSize:[10,20,30],page:1,url:'url',pageDiv:}
     * 创建一个table
     *
     * @param options
     */
    create:function(options){

        if(!this.attr("data-id")){
            this.attr('data-id',"d"+guid());
            gloabBean[this.attr('data-id')]=this;
        }else{
            return;
        }

        this.append("<thead><tr><th></th>" +
            "<template v-for='column in columns'>" +
                "<th v-if='!column.hidden'>{{column.name}}</th>" +
            "</template>" +
            "</tr></thead>");

        this.append(
            "<tbody>" +
                "<template v-for='(item , index) in rows'>"+
                    "<tr class='_sel_bg' v-if='selNum == index' v-on:click='trClick(index,item)'>" +
                        "<td>{{index + 1}}</td>" +
                        "<template v-for='column in columns'>" +
                            "<template v-if='column.addition'>" +
                                "<td v-html='item[column.field]'></td>" +
                            "</template>"+
                            "<template v-else>" +
                                "<td v-if='!column.hidden'>{{item[column.field]}}</td>" +
                            "</template>"+
                        "</template>"+
                    "</tr>" +
                    "<tr class='_bg' v-else v-on:click='trClick(index,item)'>" +
                        "<td>{{index + 1 + startWithIndex}}</td>" +
                        "<template v-for='column in columns'>" +
                            "<template v-if='column.addition'>" +
                                "<td v-html='item[column.field]'></td>" +
                            "</template>"+
                            "<template v-else>" +
                                "<td v-if='!column.hidden'>{{item[column.field]}}</td>" +
                            "</template>"+
                        "</template>"+
                    "</tr>" +
                "</template>"+
            "</tbody>");

        if(options){

            if(options.enablePage){

                this.pageList = options.pageList||[10,20,30];

                this.pageSize = options.pageSize||this.pageList[0];

                this.page = options.page||1;

                this.pageDiv = options.pageDiv;

                this.enablePage = true;

                this.sorted = options.sort;
            }

            this.url = options.url;

        }

        return this;

    },

    /**
     *  页面跳转
     * @param page
     * @param data
     */
    jumpPage:function(page,data){
        this.page = page;
        this.load(data);
        return this;
    },

    setData:function(data){

        if(data){
            
            this.page = data.page||this.page;
            this.pageSize = data.pageSize||this.pageSize;
            this.sorted = data.sort||this.sorted;

        }
        return this;

    },

    /**
     * 添加额外的列
     * 如果需要，可以在点击事件的参数中添加item属性，可以获取当前行的数据
     * 该方法需要在load前加载。
     * column:{name:列名，content:html}
     */
    addColumn:function(column,index){

        if(!column){
            return this;
        }

        var self = this;

        if(!self.additionColumns){
            self.additionColumns =  [];
        }
        column._index = (!isNaN(index)&&index>=0)||-1;
        column.field="addition"+self.additionColumns.length;
        self.additionColumns.push(column);
        return this;

    },

    getSelectRowData:function(){
        var self = gloabBean[this.attr('data-id')];
        return self.vue.rows[self.vue.selNum];
    },
    getRowDataById:function(id){
        var self = gloabBean[this.attr('data-id')];
        var datas = self.vue.rows;
        var columns = self.vue.columns;
        var idField = null;
        for(var i in columns){
            if(columns[i].id){
                idField = columns[i].field;
                break;
            }
        }
        if(!idField){
            return null;
        }
        for(var i in datas){
            if(datas[i][idField] == id){
                return datas[i];
            }
        }
        return null;
    },

    selectById:function(id){
        var self = gloabBean[this.attr('data-id')];
        var datas = self.vue.rows;
        var columns = self.vue.columns;
        var idField = null;
        for(var i in columns){
            if(columns[i].id){
                idField = columns[i].field;
                break;
            }
        }
        if(!idField){
            return null;
        }
        for(var i in datas){
            if(datas[i][idField] == id){
                self.vue.selNum = i;
                break;
            }
        }
    },

    /**
     * 从远程装载数据
     * @param data
     */
    load:function(data){
        var self = gloabBean[this.attr('data-id')] || this;
        if(self.pageVue) {
            self.pageVue.loading = true;
        }
        self.additionData = data;
        jQuery.ajax({
            data:{page:self.page,rows:self.pageSize,sort:self.sorted,param:data},
            type:'post',
            dataType:'json',
            url:self.url,
            success:function(d){
                if(d.success === true){
                    if(d.obj == null || d.obj.rows == null){
                        if(d.obj){
                            d.obj.rows=[];
                        }else{
                            d.obj={};
                            d.obj.rows=[];
                            d.obj.columns=[];
                        }
                    }
                    self.loadLocal(d.obj);
                }else{
                    if(!self.vue)
                        $(self).hide();
                    self.cleanData();
                }
            },
            error:function(){
                if(!self.vue)
                    $(self).hide();
                self.cleanData();
            }
        })

    },

    /**
     *装载本地数据
     * @param data
     */
    loadLocal:function(data){
        var self = gloabBean[this.attr('data-id')]||this;
        if(data){
            if(data.columns && self.additionColumns && self.additionColumns.length > 0){
                var idField = null;
                for(var i in data.columns){
                    if(data.columns[i].id){
                        idField = data.columns[i].field;
                        break;
                    }
                }
                for(var i in self.additionColumns){
                    var _column = self.additionColumns[i];
                    var addIndex = _column._index;
                    if(addIndex>data.columns.length){
                        addIndex = data.columns.length;
                    }
                    if(addIndex > -1){
                        data.columns.splice(addIndex,0,{field:_column.field,name:_column.name,addition:true});
                    }else{
                        data.columns.push({field:_column.field,name:_column.name,addition:true});
                    }
                    for(var i in data.rows){
                        data.rows[i][_column.field]= typeof(_column.content) === 'function'?_column.content(idField?data.rows[i][idField]:undefined,data.rows[i]):_column.content;
                    }
                }
            }
            data.page = data.page||1;
            if(!self._thatCreater){
                self._thatCreater = true;
                self.vue = new Vue({
                    el:"#"+self.attr("id"),
                    data:{
                        columns:data.columns,
                        rows:data.rows,
                        selNum:-1,
                        startWithIndex:0
                    },
                    methods:{
                        trClick:function(index,item){
                            this.selNum = index
                        }
                    }
                });
                if(self.pageDiv && self.enablePage === true){
                    $(self.pageDiv).html(
                        '<div class="pull-left navbar-form">'+
                            '<div class="form-group">'+
                                '<template v-for="i in ( pageNum > 5 ? 5 : pageNum)" >'+
                                    '<button class="btn btn-primary" v-if="(currentPage==i + showStartPage - 1)">{{i + showStartPage - 1}}</button>'+
                                    '<button class="btn btn-default" v-bind:disabled="loading" v-on:click="jumpClick(i + showStartPage - 1)" v-if="(currentPage!=i + showStartPage - 1)">{{i + showStartPage - 1}}</button>'+
                                '</template>'+
                                '<input class="form-control" style="width:50px;" type="text" v-model="textPage"/>'+
                                '<button class="btn btn-success" v-bind:disabled="loading" v-on:click="jumpClick()">跳转</button>'+
                                '<button class="btn btn-default" v-bind:disabled="currentPage==1 || loading" v-on:click="prevClick()">上一页</button>'+
                                '<button class="btn btn-default" v-bind:disabled="currentPage==pageNum || loading" v-on:click="nextClick()">下一页</button>'+
                            '</div>'+
                        '</div>'+
                        '<div class="pull-right navbar-form">'+
                            '<div class="btn-group dropup">' +
                                '<button type="button" class="btn btn-default dropdown-toggle" v-bind:disabled="loading" data-toggle="dropdown">每页{{perNum}}条记录<span class="caret"></span></button>'+
                                '<ul class="dropdown-menu" role="menu">'+
                                    '<template v-for="i in pageList">'+
                                        '<li>'+
                                            '<a href="javascript:void(0)" v-on:click="setPageSize(i)">{{i}}</a>'+
                                        '</li>'+
                                    '</template>'+
                                '</ul>'+
                            '</div>'+
                            '<div class="btn" style="cursor:default;">共{{pageNum}}页，{{total}}条记录</div>'+
                        '</div>'
                    );
                    var pageNum = parseInt((data.total-1)/self.pageSize)+1;
                    var startPage = calcStartPage(self.pageSize,data.total,data.page);
                    self.pageVue = new Vue({
                        el:self.pageDiv,
                        data:{
                            currentPage:data.page||1,
                            textPage:data.page||1,
                            showStartPage:startPage,
                            total:data.total,
                            pageNum:pageNum,
                            perNum:self.pageSize,
                            pageList:self.pageList,
                            loading:false
                        },
                        methods:{
                            jumpClick:function(page,event){
                                if(page){
                                    if(page != this.currentPage){
                                        this.textPage = page;
                                        this.currentPage = page;
                                        this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                                    }else{
                                        return;
                                    }
                                }else{
                                    if(this.textPage == this.currentPage){
                                        return;
                                    }
                                    this.textPage= isNaN(this.textPage)?1:this.textPage;
                                    this.textPage=this.textPage<1?1:(this.textPage > this.pageNum?this.pageNum:this.textPage);
                                    this.currentPage=this.textPage;
                                    this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                                }
                                self.page = this.currentPage;
                                if(self.url)
                                    self.load(self.additionData);
                            },
                            prevClick:function(event){
                                this.currentPage = this.currentPage<=1?1:this.currentPage-1;
                                this.textPage=this.currentPage;
                                this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                                self.page = this.currentPage;
                                if(self.url)
                                    self.load(self.additionData);
                            },
                            nextClick:function(event){
                                alert(this.pageNum+"  "+this.currentPage);
                                this.currentPage = this.currentPage>=this.pageNum?this.pageNum:Number(this.currentPage)+1;
                                this.textPage=this.currentPage;
                                this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                                self.page = this.currentPage;
                                if(self.url)
                                    self.load(self.additionData);
                            },
                            setPageSize:function(size){
                                if(this.perNum == size){
                                    return;
                                }
                                this.perNum=size;
                                this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                                this.pageNum=parseInt((this.total-1)/this.perNum)+1;
                                if(this.currentPage>this.pageNum){
                                    this.currentPage=this.pageNum;
                                    this.textPage=this.pageNum;
                                }
                                self.pageSize = this.perNum;
                                self.page=this.currentPage;
                                if(self.url)
                                    self.load(self.additionData);
                            }
                        }
                    });
                }
            }else{
                self.vue.rows=data.rows;
                self.vue.selNum=-1;
                var pageNum = parseInt((data.total-1)/self.pageSize)+1;
                var startPage = calcStartPage(self.pageSize,data.total,data.page);
                self.pageVue.total=data.total;
                self.pageVue.pageNum=pageNum;
                self.pageVue.showStartPage=startPage;
                self.pageVue.currentPage=data.page;
                self.pageVue.textPage=data.page;
            }
            self.vue.startWithIndex=(self.pageVue.currentPage-1)*self.pageVue.perNum;
            self.pageVue.loading=false;
            $(self).css('display','table');
        }
    },
    cleanData:function(){
        var self = gloabBean[this.attr('data-id')]||this;;
        if(self.vue && self.vue.rows)
            self.vue.rows.splice(0,self.vue.rows.length);
        self.page = 1;
        if(self.pageVue){
            self.pageVue.total=0;
            self.pageVue.pageNum=1;
            self.pageVue.showStartPage=1;
            self.pageVue.currentPage=1;
            self.pageVue.textPage=1;
            self.pageVue.loading=false;
        }
    }

})

function calcStartPage(pageSize,total,page){
    var pageNum = parseInt((total-1)/pageSize)+1; //8
    var startPage = pageNum <= 5 ? 1 : (page <= 2 ? 1:page-2 ); //6
    if(pageNum > 5)
        if(startPage >= pageNum - 3){
            startPage = pageNum - 4;
        }
    return startPage;
}

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}