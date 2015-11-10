$.widget('custom.applyEvent', {
	options : {
		urlContext : "",
		guestIds:"",
		guestsNames: {}

	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		this.urlContext = this.options.urlContext;
		this.guestIds = this.options.guestIds;
		this.guestsNames = this.options.guestsNames;
		this.date = this.element.find("#date");
		this.inputGuest = this.element.find("#inputGuest");
		this.hiddenGuestsIds  = this.element.find("#hiddenGuestsIds");
		this.ulGuests = this.element.find("#ulGuests");
	},

	_initialize : function() {
		this._createDatePicker();
		this._createAtocompletar();
		this._loadGuestIds();
		this._loadGuestNames();
	},

	_bindEvents : function() {
		this.element.find("#btn-addUser").click($.proxy(this._addUser, this));
	},
	
	_addUser: function() {
		var url = "#?id=" + this.element.find("#user-id").val();
		$('<li class="li-user" value='+this.element.find("#hiddenGuestId").val()+ '>' + this.inputGuest.val() +' <a href="'+url+'">Eliminar</a></li>').appendTo(this.ulGuests);
		var string ="";
		var array = this.element.find(".li-user");
		for(var i = 0; i < array; i++){
			if(i == (array.lemgth -1)){
				string+= array[i].value.toString();
			} else {
				string+= array[i].value.toString() + ",";
			}
		}
		
	},
	
	_createDatePicker : function() {
		this.date.datepicker();
	},
	
	_createAtocompletar : function() {
		this.inputUsers.autocomplete({
		 	minLength: 3,
			source : function(request, response) {
		    	var url = "${pageContext.request.contextPath}/services/getUsers";
				$.ajax({
					url : url,
					type: "POST",
					data: { term: request.term },
					dataType : "json",
					success : function(data) {
						response($.map(data.users, function(item) {
							return {
								label : item.label,
								value : item.label,
								key : item.value
							};
						}));
					}
				});
			},
			select: function (event, ui) {
		        this.element.find("#user-Id").val(ui.item.key);
		    }
		});
	},
	
	_loadGuestIds : function() {
		this.hiddenGuestsIds.val(this.guestIds);
	},
	
	_loadGuestNames : function() {
		for(var i=0; i<this.guestsNames.length; i++){
			var guest = this.guestsNames[i].split(",");   
			$('<li class="li-user" value='+guest[1]+ '>' + guest[0] +' <a href="'+url+'">Eliminar</a></li>').appendTo(this.ulGuests);
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
