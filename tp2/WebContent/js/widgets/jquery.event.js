$.widget('custom.applyEvent', {
	options : {
		urlContext : "",
		isMeeting : false,
		isPrivateEvent : false
	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		debugger;
		this.urlContext = this.options.urlContext;
		this.isMeeting = this.options.isMeeting;
		this.isPrivateEvent = this.options.isPrivateEvent;
		this.startTime = this.element.find("#startTimepicker");
		this.endTime = this.element.find("#endTimepicker");
		if(this.isMeeting){
			this.inputUsers = this.element.find("#inputGuests");
			this.inputHalls = this.element.find("#inputHalls");
			this.input
		}
	},

	_initialize : function() {
		this._createDateTimePicker();
		if (this.isMeeting) {
			this._callbackUsers();
			this._callbackHalls();
		}
	},

	_bindEvents : function() {
	},

	_createDateTimePicker : function() {
		this.startTime.datetimepicker();
		this.endTime.datetimepicker();
	},
	
	_callbackUsers: function() {
		var url = this.urlContext + "/services/getUsers";
		this.inputUsers.autocomplete({
			source : function(request, response) {
				$.ajax({
					url : url,
					contentType : "application/json;charset=UTF-8",
					success : function(data) {
						response(data.users);
					},
					error : function() {
						alert("Error al obtener los invitados");
					}
				});
			}
		});
	},
	
	_callbackHalls: function() {
		var url = this.urlContext + "/services/getHalls";
		this.inputHalls.autocomplete({
			source : function(request, response) {
				$.ajax({
					url : url,
					contentType : "application/json;charset=UTF-8",
					success : function(data) {
						response(data.halls);
					},
					error : function() {
						alert("Error al obtener las salas");
					}
				});
			}
		});
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
