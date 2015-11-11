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
//		this.week = new Array();
	},

	_initialize : function() {
		this._loadEventsCallback();
	},

	_bindEvents : function() {
		
	},
	
	_loadEventsCallback: function() {
		debugger;
		var url = this.urlContext + "/services/getEvents";
		var d = new Date();
		date = d.getMonth()+'/' + d.getDate() + '/' + d.getFullYear()
		$.ajax({
			url : url,
			type: "POST",
			data: date,
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
//		 this.week = week;
//		 $('<div>' + week[0] + '</div>')appendTo(this.table.find("sundayId"));
//		 $('<div>' + week[1] + '</div>')appendTo(this.table.find("mondayId"));
//		 $('<div>' + week[2] + '</div>')appendTo(this.table.find("tuesdayId"));
//		 $('<div>' + week[3] + '</div>')appendTo(this.table.find("wednesdayId"));
//		 $('<div>' + week[4] + '</div>')appendTo(this.table.find("thursdayId"));
//		 $('<div>' + week[5] + '</div>')appendTo(this.table.find("fridayId"));
//		 $('<div>' + week[6] + '</div>')appendTo(this.table.find("saturdayId"));
	},
	
	__loadBody: function(data) {
		var events = data.events; 
		for(var i = 0 ; i < events.length; i++){
			debugger;
		}
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
