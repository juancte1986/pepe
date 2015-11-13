$.widget('custom.applyCalendar', {
	options : {
		urlContext : "",
	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		this.urlContext = this.options.urlContext;
		this.table = this.element.find(".table");
		this.week = new Array();
	},

	_initialize : function() {
		this._loadEventsCallback(this._getCurrentDate());
	},

	_bindEvents : function() {
		this.element.find("#btn-next").click($.proxy(this._loadNextCalendar,this));
		this.element.find("#btn-previous").click($.proxy(this._loadPreviuosCalendar,this));
	},
	
	_getCurrentDate : function (){
		var date = new Date();
		var formCalendar = {};
		debugger;
		formCalendar.date= date.getDate().toString() +'/' +  (date.getMonth() + 1).toString() + '/' + date.getFullYear().toString() ;
		formCalendar.actualPage = 0;
		return formCalendar;
	},
	
	_loadNextCalendar: function() {
		var formCalendar = {};
		formCalendar.date = this.week[6];
		formCalendar.actualPage = 1;
		this._loadEventsCallback(formCalendar);
	},
	
	_loadPreviuosCalendar : function() {
		var formCalendar = {};
		formCalendar.date = this.week[0];
		formCalendar.actualPage = -1;
		this._loadEventsCallback(formCalendar);
	},
	
	_loadEventsCallback: function(formCalendar) {
		var url = this.urlContext + "/services/getEvents";
		$.ajax({
			url : url,
			type: "POST",
			data: JSON.stringify(formCalendar),
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			success : $.proxy(this._loadEvents, this),
			error : function() {
				alert("Error al obtener los eventos");
			}
		});
	},
	
	_loadEvents : function(data) {
		if(!data.error){
			this._loadThead(data.week);
			this._loadBody(data);
		} else{
			alert(data.message);
		}
	},
	
	_loadThead: function(week) {
		 this.week = week;
		 this.table.find("#sundayId").html('<div>'+this.week[0]+'</div>');
		 this.table.find("#mondayId").html('<div>'+this.week[1]+'</div>');
		 this.table.find("#tuesdayId").html('<div>'+this.week[2]+'</div>');
		 this.table.find("#wednesdayId").html('<div>'+this.week[3]+'</div>');
		 this.table.find("#thursdayId").html('<div>'+this.week[4]+'</div>');
		 this.table.find("#fridayId").html('<div>'+this.week[5]+'</div>');
		 this.table.find("#saturdayId").html('<div>'+this.week[5]+'</div>');
	},
	
	_loadBody: function(data) {
//		var events = data.events; 
//		for(var i = 0 ; i < events.length; i++){
//			debugger;
//		}
	},
	
	destroy : function() {
		for ( var name in this.calls) {
			this.calls[name].abort();
			this.calls[name] = null;
		}

		if (typeof (CollectGarbage) == "function") {
			CollectGarbage();
		}
	}

});
