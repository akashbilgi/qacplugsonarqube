define(['config', 'dom'], function(config, dom) {
	return {
		create: function(projectKey) {
			return {
				projectKey: projectKey,
				show: function(parent) {
					//dom.createElement(parent, 'h2', { className: 'issueresolver-header', textContent: ''});
					dom.createElement(parent, 'h2', { className: 'issueresolver-description big-spacer-bottom', textContent: 'Please check sonar-scanner properties before proceeding'});
					
					// Export - form
					var formExport = dom.createElement( parent, 'form', { id: 'issueresolver-export-form' });

					// Export - form - button
					var formExportButton = dom.createElement(formExport, 'div', { className: 'modal-field'});
					dom.createElement(formExportButton, 'button', { textContent: 'RUN'});

					// Export - form - onsubmit
					formExport.onsubmit = function() {
						window.location = config.basename + 'api/issueresolver/export?projectKey=' + encodeURI(projectKey);
						//setTimeout('', 5000);
						//window.location = config.basename;
						
						return false;
					};
				}
			};
		}
	};	
});
