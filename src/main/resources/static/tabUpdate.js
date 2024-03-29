define(['dom', 'result'], function(dom, result) {
	return {
		create: function(projectKey) {	
			return {
				projectKey: projectKey,
				show: function(parent) {
					// Header and description
					dom.clear();
					dom.createElement(parent, 'h2', { className: 'issueresolver-header', textContent: 'Run QAC'});
					dom.createElement(parent, 'h2', { className: 'issueresolver-description big-spacer-bottom', textContent: 'Invoke QAC analysis of the project'});
					
					// Update - form
					var formUpdate = dom.createElement(parent, 'form', { id: 'issueresolver-update-form' });

					// Update - form - projectKey (hidden)
					dom.createElement(formUpdate, 'input', { id: 'issueresolver-update-projectKey', name: 'projectKey', value: projectKey });
					
					// Update - form - project key
					var formUpdateProjectKey = dom.createElement(formUpdate, 'div', { className: 'modal-field' });		
					var formUpdateProjectKeyLabel = dom.createElement(formUpdateProjectKey, 'label', { for: 'issueresolver-update-fromprojectkey' });
					formUpdateProjectKeyLabel.appendChild(document.createTextNode('Project'));
					dom.createElement(formUpdateProjectKeyLabel, 'em', { className:'mandatory', textContent:'*' });
					var formUpdateProjectKeyInput = dom.createElement(formUpdateProjectKey, 'select', { id: 'issueresolver-update-fromprojectkey', name: 'fromProjectKey' });
					dom.createElement(formUpdateProjectKey, 'div', {className:'modal-field-description', textContent: 'The project to update issues from'});
			 
					// Update - form - preview (checkbox, optional)
					var formUpdatePreview = dom.createElement(formUpdate, 'div', { className: 'modal-field' });				
					var formUpdatePreviewLabel = dom.createElement(formUpdatePreview, 'label', { for: 'issueresolver-update-preview' });
					formUpdatePreviewLabel.appendChild(document.createTextNode('Preview'));
					dom.createElement(formUpdatePreview, 'input', { id: 'issueresolver-update-preview', type: 'checkbox', name: 'preview', value: 'true'});
					dom.createElement(formUpdatePreview, 'div', { className: 'modal-field-description', textContent: 'If set, issues are not actually resolved, but only matched and checked, no changes are made' });
				
					// Update - form - skipAssign (checkbox, optional)
					var formUpdateSkipAssign = dom.createElement(formUpdate, 'div', { className: 'modal-field' });				
					var formUpdateSkipAssignLabel = dom.createElement(formUpdateSkipAssign, 'label', { for: 'issueresolver-update-skipassign' });
					formUpdateSkipAssignLabel.appendChild(document.createTextNode('Skip assignments'));
					dom.createElement(formUpdateSkipAssign, 'input', { id: 'issueresolver-update-skipassign', type: 'checkbox', name: 'skipAssign', value: 'true'});
					dom.createElement(formUpdateSkipAssign, 'div', { className: 'modal-field-description', textContent: 'If set, issue assignments are skipped' });

					// Update - form - skipComments (checkbox, optional)
					var formUpdateSkipComments = dom.createElement(formUpdate, 'div', { className: 'modal-field' });				
					var formUpdateSkipCommentsLabel = dom.createElement(formUpdateSkipComments, 'label', { for: 'issueresolver-update-skipcomments' });
					formUpdateSkipCommentsLabel.appendChild(document.createTextNode('Skip comments'));
					dom.createElement(formUpdateSkipComments, 'input', { id: 'issueresolver-update-skipcomments', type: 'checkbox', name: 'skipComments', value: 'true'});
					dom.createElement(formUpdateSkipComments, 'div', { className: 'modal-field-description', textContent: 'If set, issue comments are skipped' });
				
					// Update - form - button
					var formUpdateButton = dom.createElement(formUpdate, 'div', { className: 'modal-field' });
					var formUpdateButtonButton = dom.createElement(formUpdateButton, 'button', { textContent: 'Update' });

					// Result placeholder
					//var divUpdateResult = dom.createElement(parent, 'div', {});
					//divUpdateResult.style.display = 'none';
					//dom.createElement(divUpdateResult, 'h2', { className: 'issueresolver-header', textContent: 'Update result'});
					
					// Update - form - onsubmit
					/*formUpdate.onsubmit = function() {
						formUpdateButtonButton.disabled=true;
								
						window.SonarRequest.postJSON(
						    '/api/issueresolver/update',
						    new FormData(formUpdate)
						).then(function(response) {
							divUpdateResult.appendChild(result.formatResult('Update', response));
							divUpdateResult.style.display='block';
							formUpdateButtonButton.disabled=false;
						}).catch(function (error) {
							divUpdateResult.appendChild(result.formatError('Update', error));
							divUpdateResult.style.display='block';
							formUpdateButtonButton.disabled=false;
						});
						*/
						return false;
					};
					
					// Populate project key drop down list
					window.SonarRequest.postJSON(
						'/api/components/search',
						{ 'ps':999999,'qualifiers':'TRK'}		
					).then(function(response) {
						for(var componentIndex = 0; componentIndex < response.components.length; componentIndex++) {
							var component = response.components[componentIndex];
							dom.createElement(formUpdateProjectKeyInput, 'option', { value: component.key, textContent: component.name });
						}
					}).catch(function(error) {
						// Nothing
					});
				}
			};
		}
	};
});
