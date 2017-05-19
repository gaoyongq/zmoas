var gloabBean_Page = {};
//{page:1,pageSize:10,pageList:[10,20,30],total:100}
jQuery.extend(jQuery.fn, {
    create:function(options){
        if(!this.attr("data-id")){
            this.attr('data-id',"d"+guid());
            gloabBean_Page[this.attr('data-id')]=this;
        }else{
            return;
        }
        var self = gloabBean_Page[this.attr('data-id')]||this;
        self.page = options.page;
        self.pageSize = options.pageSize;
        self.pageList = options.pageList;
        self.total = options.total;
        if(!self._thatCreater){
            self._thatCreater = true;
            $(self).html(
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
            var pageNum = parseInt((self.total-1)/self.pageSize)+1;
            var startPage = calcStartPage(self.pageSize,self.total,self.page);
            self.pageVue = new Vue({
                el:"#"+$(self).attr("id"),
                data:{
                    currentPage:self.page||1,
                    textPage:self.page||1,
                    showStartPage:startPage,
                    total:self.total,
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
                        if(self.load && typeof(self.load) === 'function')
                            self.load({page:self.page,pageSize:self.pageSize});
                    },
                    prevClick:function(event){
                        this.currentPage = this.currentPage<=1?1:this.currentPage-1;
                        this.textPage=this.currentPage;
                        this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                        self.page = this.currentPage;
                        if(self.load && typeof(self.load) === 'function')
                            self.load({page:self.page,pageSize:self.pageSize});
                    },
                    nextClick:function(event){
                        this.currentPage = this.currentPage>=this.pageNum?this.pageNum:Number(this.currentPage)+1;
                        this.textPage=this.currentPage;
                        this.showStartPage = calcStartPage(this.perNum,this.total,this.currentPage);
                        self.page = this.currentPage;
                        if(self.load && typeof(self.load) === 'function')
                            self.load({page:self.page,pageSize:self.pageSize});
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
                        if(self.load && typeof(self.load) === 'function')
                            self.load({page:self.page,pageSize:self.pageSize});
                    }
                }
            });
        }else{
            var pageNum = parseInt((self.total-1)/self.pageSize)+1;
            var startPage = calcStartPage(self.pageSize,self.total,self.page);
            self.pageVue.total=self.total;
            self.pageVue.pageNum=pageNum;
            self.pageVue.showStartPage=startPage;
            self.pageVue.currentPage=self.page;
            self.pageVue.textPage=self.page;
        }
        self.pageVue.loading=false;
        return this;
    },
    setPage:function(page){
        var self = gloabBean_Page[this.attr('data-id')]||this;
        self.page = !isNaN(page)&&page>0?page:1;
        var maxPage =parseInt((self.total-1)/self.pageSize)+1;
        if(self.page>maxPage){
            self.page=maxPage;
        }
        self.pageVue.showStartPage = calcStartPage(self.pageSize,self.total,self.page);
        self.pageVue.currentPage=self.page;
        self.pageVue.textPage=self.page;
    },
    setTotal:function(total){
        var self = gloabBean_Page[this.attr('data-id')]||this;
        self.total = !isNaN(total)&&total>0?total:self.total;
        self.pageVue.total = self.total;
        self.page = !isNaN(page)&&page>0?page:1;
        var maxPage =parseInt((self.total-1)/self.pageSize)+1;
        if(self.page>maxPage){
            self.page=maxPage;
        }
        var pageNum = parseInt((self.total-1)/self.pageSize)+1;
        var startPage = calcStartPage(self.pageSize,self.total,self.page);
        self.pageVue.total=self.total;
        self.pageVue.pageNum=pageNum;
        self.pageVue.showStartPage=startPage;
        self.pageVue.currentPage=self.page;
        self.pageVue.textPage=self.page;
    },
    onPageChange:function(callback){
        var self = gloabBean_Page[this.attr('data-id')]||this;
        self.load = callback;
    }
})

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

function calcStartPage(pageSize,total,page){
    var pageNum = parseInt((total-1)/pageSize)+1; //8
    var startPage = pageNum <= 5 ? 1 : (page <= 2 ? 1:page-2 ); //6
    if(pageNum > 5)
        if(startPage >= pageNum - 3){
            startPage = pageNum - 4;
        }
    return startPage;
}