 $(document).ajaxStart(function(){
    $("#wait").css("display", "block");
});

$(document).ajaxComplete(function(){
    $("#wait").css("display", "none");
}); 

function otherSelect() {
                var path = document.getElementById("path");
                var url =document.getElementById("url");
                var com =document.getElementById("com");
                var ip1 =document.getElementById("ip1");
                var pass1 =document.getElementById("pass1");
                
                if (document.forms[0].theItems.options[document.forms[0].theItems.selectedIndex].value == "item1") {
                    path.style.visibility = "visible";
                    url.style.visibility= "hidden";
                    com.style.visibility="hidden";
                    ip1.style.visibility="hidden";
                    pass1.style.visibility="hidden";
                }
                else if (document.forms[0].theItems.options[document.forms[0].theItems.selectedIndex].value == "item2") {
                	 path.style.visibility = "hidden";
                     url.style.visibility= "visible";
                     com.style.visibility="hidden";
                     ip1.style.visibility="hidden";
                     pass1.style.visibility="hidden";
                } 
                else if (document.forms[0].theItems.options[document.forms[0].theItems.selectedIndex].value == "item3") {
                	 path.style.visibility = "hidden";
                     url.style.visibility= "hidden";
                     com.style.visibility="visible";
                     ip1.style.visibility="visible";
                     pass1.style.visibility="visible";
                } 
                else
                	{
                	 path.style.visibility = "hidden";
                     url.style.visibility= "hidden";
                     com.style.visibility="hidden";
                     ip1.style.visibility="hidden";
                     pass1.style.visibility="hidden";
                	}
            }


function proceed (curObj) {
	if(curObj) {
		var act = $(curObj).attr('act');
		var mode   = $(curObj).attr('mode');
		var osid   = $(curObj).attr('osid');
		var softwarid = $(curObj).attr('softwarid');
		var softwarname = $(curObj).attr('softwarname');
		var osname   = $(curObj).attr('osname');
		var ip = $("#ip").val();
		var env = $("#env").val();
		var ver = $("#ver").val();
		var fil = $("#fil").val();
		var user = $("#user").val();
		var root = $("#rootpassword").val();
		var rootD = $("#rootpasswordD").val();
		var ipD = $("#ipD").val();
		var url = $("#url").val();
		var path = $("#path").val();
		var soname = $("#soname").val();
		var dep = $("#dep").val();
		var com = $("#com").val();
		var ip1 = $("#ip1").val();
		var pass1 = $("#pass1").val();
		
		
		
		$.ajax({
			method: "POST",
			url: "servlet4",
			dataType: 'json',
			data: { "act": act, "mode": mode , "osid":osid , "softwarid":softwarid , "softwarname":softwarname,"osname":osname,"ip":ip,"root":root,"env":env,"fil":fil,"ver":ver,"ipD":ipD,"rootpasswordD":rootD,"user":user,"soname":soname,"url":url,"path":path,"dep":dep,"com":com,"ip1":ip1,"pass1":pass1}
		})
		.done(function( data ) {
			
			if(mode=="software")
			{
				
				switch (act) {


				case 'oslist':
					if (data && data.length > 0) {
						var html =  '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick="window.location.reload()" mode="software" action="oslist">Back</button><br/><br/>';	
						for (var i=0; i< data.length; i++) {
							
							html += '<div class="col-lg-4">' +
								'<div class="panel panel-yellow">' +
									'<div class="panel-heading">' +
										'<h3 class="panel-title">' + (data[i]).os_name + ' <span class="pull-right glyphicon glyphicon-play" onclick="proceed(this)" act="softwarelist" mode="' + mode + '" osid="'+data[i].os_id+'" osname="'+data[i].os_name+'"></span></h3>' +
									'</div>' +
									'<div class="panel-body">' +
										'<div class="pull-left"><img src="'+data[i].os_name+'.jpg" alt="" height="250" width="250" /></div>' +
					
									'</div>' +
								'</div>' +
							'</div>';
						}
						$('#page-content').html(html);
					//	$('.page-header').html($('.page-header').html() +  ' > ' + act);
						$('#modeaccordion').hide();
			
						}
						

					
				break;



				case 'softwarelist':

					if (data && data.length > 0) {
						var html = '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick=" proceed(this)" act="oslist" mode="' + mode + '" osid="' + osid + '"  >Back</button><br/><br/>' ;

						html +='<div class="abc"style = "margin-top:-50px;" >'+ '<a href="javascript:void(0);" style="margin-left:820px;color:black;font-size:20px;  font-family: sans-serif;" onclick="proceed(this)" act="addnew" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'">Click here to add new software</a>'+
						'</div>'+'<br>';
						
						for (var i=0; i< data.length; i++) {
							html += '<div class="col-lg-4">' +
									'<div class="panel panel-yellow">' +
									'<div class="panel-heading">' +
										'<h3 class="panel-title">' + (data[i]).soft_name+ ' <span class="pull-right glyphicon glyphicon-play" onclick="proceed(this)" act="submitsoftware" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'" softwarid="' + (data[i]).soft_id + '"softwarname="'+(data[i]).soft_name+'"></span></h3>' +
									'</div>' +
									'<div class="panel-body">' +
										'<div class="pull-left"><img src="'+data[i].soft_name+'.jpg" alt="" height="150" width="150" onerror="if (this.src != \'images.jpg\') this.src = \'images.jpg\'	;"/></div>' +
										
									'</div>' +
								'</div>' +
							'</div>';
						}

						$('#search').attr('act', 'search');
						$('#search').attr('mode', mode);
						$('#search').attr('osid', osid);
						$('#search').attr('osname', osname);
						
						//$('.page-header').html($('.page-header').html() +  ' > ' + act);

						$('#page-content').html(html);
						$('#search').show();
						$('#search').keypress(function(e){
					        if(e.which == 13){//Enter key pressed
					        	var Value = document.getElementById('search').value;
					        	$('#search').attr('softwarname',Value);
					        	proceed(this);
					           // alert('Enter pressed: Submitting the form....');
					        }
					    });
						
						$('#modeaccordion').hide();
						$('#lnkAddNewSoft').attr('display', 'block');
						$('#lnkAddNewSoft').show();
						$('.abc').show();
										
					}					
				break;
				
				case 'search':

					if (data && data.length > 0) {
						var html = '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick=" proceed(this)" act="softwarelist" mode="' + mode + '" osid="' + osid + '"  >Back</button><br/><br/>' ;
						var html1 = '';
						if(data[0].sname != "null")
						{
						

						
						for (var i=0; i< data.length; i++) {
							html += '<div class="col-lg-4">' +
									'<div class="panel panel-yellow">' +
									'<div class="panel-heading">' +
										'<h3 class="panel-title">' + (data[i]).soft_name + ' <span class="pull-right glyphicon glyphicon-play" onclick="proceed(this)" act="submitsofware" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'" softwarid="' + (data[i]).soft_id + '"softwarname="'+(data[i]).soft_name+'"></span></h3>' +
									'</div>' +
									'<div class="panel-body">' +
										'<div class="pull-left"><img src="'+data[i].soft_name+'.jpg" alt="" height="150" width="150" onerror="if (this.src != \'images.jpg\') this.src = \'images.jpg\'	;" /></div>' +
										
									'</div>' +
								'</div>' +
							'</div>';
						}
						}
						else
							{


							html +='<div class="abc" >'+ '<h3 onclick="proceed(this)" act="addnew" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'">Add new software</h3>'+
							'</div>'+'<br>';

							html += '<div class="col-lg-4">' +
							'<p class="heading">No Results Found</p>'+
					'</div>';
							}

					
						$('#page-content').html(html);
						
						$('#search').show();
						$('#search').keypress(function(e){
					        if(e.which == 13){//Enter key pressed
					        	$('#search').attr('act', 'search');
								$('#search').attr('mode', mode);
								$('#search').attr('osid', osid);
								
								
					        	var Value = document.getElementById('search').value;
					        	$('#search').attr('softwarname',Value);
					        	proceed(this);
					           // alert('Enter pressed: Submitting the form....');
					        }
					    });
						
						$('#modeaccordion').hide();
						$('#lnkAddNewSoft').attr('display', 'block');
						$('#lnkAddNewSoft').show();
						$('#abc').show();
					
					}
					

				
					
				break;
				
				


				case 'submitsoftware':
					$('#search').hide();
			var html = '<button type="submit" class="btn btn-default" onclick=" proceed(this)" act="softwarelist" mode="' + mode + '" osid="' + osid + '"  >Back</button><br/><br/>' ;	
			html += 
						'<div class="form-group">' +
					    	'<label>OS Name : ' + data[0].oname + '</label>' +
						'</div>' +
						'<div class="form-group">' +
					    	'<label>software Name : ' + data[0].sname + '</label>' +
						'</div>' +
						'<div class="form-group">' +
					    	'<label for="ip" >IP</label>' +
					    	'<input type="ip" class="form-control" id="ip" placeholder="IP">' +
						'</div>' +
						'<div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:35%;left:45%;padding:2px;">'+'<img src=\'demo_wait.gif\' width="64" height="64" /><br>Loading..</div>'+

					  	'<div class="form-group">' +
					    	'<label for="rootpassword">Root Password</label>' +
					    	'<input type="password" class="form-control" id="rootpassword" placeholder="Root Password">' +
					  	'</div>' +
					  	'<button type="submit" class="btn btn-default" onclick=" proceed(this)" act="submit" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'" softwarid="' + softwarid + '"softwarname="'+softwarname+'"  >Submit</button>' ;
			
					$('#page-content').html(html);
					//$('.page-header').html($('.page-header').html() +  ' > ' + act);
					$('#modeaccordion').hide();
					$('#abc').hide();
				break;
						


				case 'submit':
					$('#search').hide();
					if(data[0].error == 0)
						{
					
						 alert('Error in installing the software.. Please try again');
				
						}
					else
						{
						var html = '';
						 window.alert('Software installed successfully');
						html +='<div class="final" >'+ '<h3>Thankyou for downloading</h3>'+
						'</div>'+'<br>';
						}
						$('#page-content').html(html);
						$('#modeaccordion').hide();
						$('#abc').hide();
						
			
				break;

				case 'addnew':
					$('#search').hide();
					var html = '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick=" proceed(this)" act="softwarelist" mode="' + mode + '" osid="' + osid + '"  >Back</button><br/><br/>' ;
					html += 
					'<div class="form-group">' +
				    	'<label>OS Name : ' + data[0].oname + '</label>' +
					'</div>' +
					'<div class="form-group">' +
				    	'<label for="soname">Software Name</label>' +
				    	'<input type="soname" class="form-control" id="soname" placeholder="Enter Software">' +
					'</div>' +
					'<div class="form-group">' +
			    	'<label for="dep">Dependency</label>' +
			    	'<input type="dep" class="form-control" id="dep" placeholder="yes or no">' +
				'</div>' +
					'<div class="form-group">' +
			    	'<label for="soname">URL or Path or commands</label>' +
			    	//' <select id = "drop" name="theItems"  onchange="otherSelect()" > <option value="" selected disabled>-----------SELECT-------------</option> <option value="item1">Path To The Installer</option> <option value="item2">Url Of The Installer</option><option value="item3">Commands</option> </select>' +
				'</div>' +
				  	'<div class="form-group">' +
				    	
				    	'<input type="text" class="form-control" id="url"  placeholder="Enter URL">' +'<br>'+
				    	'<p>OR</p>'+
				  	'</div>' +
				  	'<div class="form-group" >' +
			    	
			    	'<input type="path" class="form-control" id="path"  placeholder="Enter Path">' +'<br>'+
			    	'<p>OR</p>'+
			  	'</div>' +
				'<div class="form-group" >' +
		    	'<input type="text" class="form-control" id="com" placeholder="commands">' +
			'</div>' +
			'<div class="form-group" >' +
	    	'<input type="text" class="form-control" id="ip1" placeholder="IP">' +
		'</div>' +
		'<div class="form-group" >' +
    	'<input type="password" class="form-control" id="pass1" placeholder="Password">' +
	'</div>' +
	'<button type="submit" class="btn btn-default" onclick="proceed(this)"  act="submitnew" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'">Submit</button>' +
					'<div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:35%;left:45%;padding:2px;">'+'<img src=\'demo_wait.gif\' width="64" height="64" /><br>Loading..</div>';
							
					$('#page-content').html(html);
				$('.page-header').html($('.page-header').html() +  ' > ' + action);
				$('#modeaccordion').hide();

			break;
			
			
				


				case 'submitnew':
					$('#search').hide();
					if(data[0].error == 1)
						{
					
						 alert('Software not present');
					
						}
					else if(data[0].error == 0)
						{
						var html = '';
						 window.alert('software already present in the database');
						html +='<div class="final" >'+ '<h3 onclick="proceed(this)" act="softwarelist" mode="' + mode + '" osid="'+osid+'" osname="'+osname+'">Click here to install the software</h3>'+
						'</div>'+'<br>';
						}
					
					else
					{
					var html = '';
					 window.alert('software added');
					html +='<div class="final" >'+ '<h3 onclick="proceed(this)" act="softwarelist" mode="' + mode + '" osid="'+osid+'" osname="'+osname+'">Click here to install the software</h3>'+
					'</div>'+'<br>';
					}
						$('#page-content').html(html);
						$('#modeaccordion').hide();
						$('#abc').hide();
						
			
				break;


				}
			
			
			
			
		}	
			else if(mode=="application")
				{
				switch (act) {

				case 'oslist':
					if (data && data.length > 0) {
						var html =  '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick="window.location.reload()" mode="software" action="oslist">Back</button><br/><br/>';
							
						for (var i=0; i< data.length; i++) {
							
							html += '<div class="col-lg-4">' +
								'<div class="panel panel-yellow">' +
									'<div class="panel-heading">' +
										'<h3 class="panel-title">' + (data[i]).os_name + ' <span class="pull-right glyphicon glyphicon-play" onclick="proceed(this)" act="submitapp" mode="' + mode + '" osid="'+data[i].os_id+'" osname="'+data[i].os_name+'"></span></h3>' +
									'</div>' +
									'<div class="panel-body">' +
										'<div class="pull-left"><img src="'+data[i].os_name+'.jpg" alt="" height="250" width="250" /></div>' +
					
									'</div>' +
								'</div>' +
							'</div>';
						}

						$('#page-content').html(html);
					//	$('.page-header').html($('.page-header').html() +  ' > ' + act);
						$('#modeaccordion').hide();
					}
				break;

				case "submitapp":
					var html = '<button type="submit" class="btn btn-default" onclick=" proceed(this)" act="oslist" mode="' + mode + '" osid="' + osid + '"  >Back</button><br/><br/>' ;
					html += 
						'<div class="form-group">' +
					    	'<label>OS Name : ' + data[0].oname + '</label>' +
						'</div>' +
						'<div class="form-group">' +
						'</div>' +
						'</div>' +
						'<div class="form-group">' +
				    	'<label for="environment">Environment</label>' +
				    	' <select id = "env" name="theItems" > <option value="" selected disabled>-----------SELECT-------------</option> <option value="NodeJS">NodeJS</option> <option value="Tomcat">Tomcat</option></select>' +
					'</div>' +
					'<div class="form-group">' +
			    	'<label for="ver">VERSION</label>' +
			    	'<input type="version" class="form-control" id="ver" placeholder="Version">' +
				'</div>' +
				'<div class="form-group">' +
		    	'<label for="fil">FILE</label>' +
		    	'<input type="fil" class="form-control" id="fil" placeholder="File">' +
			'</div>' +

			'<div class="form-group">' +
		    	'<label for="ip">IP</label>' +
		    	'<input type="ip" class="form-control" id="ipD" placeholder="IP">' +
			'</div>' +
		
			'<div class="form-group">' +
	    	'<label for="user">USERNAME</label>' +
	    	'<input type="user" class="form-control" id="user" placeholder="Username">' +
		'</div>' +  	'<div class="form-group">' +
					    	'<label for="rootpassword">Root Password</label>' +
					    	'<input type="password" class="form-control" id="rootpasswordD" placeholder="Root Password">' +
					  	'</div>' +'<div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:40%;left:45%;padding:2px;">'+'<img src=\'demo_wait.gif\' width="64" height="64" /><br>Loading..</div>'+

					  	'<button type="submit" class="btn btn-default" onclick="proceed(this)" act="deploy" mode="' + mode + '" osid="' + osid + '" osname="'+osname+'" >Deploy</button>' ;

					$('#page-content').html(html);
				//	$('.page-header').html($('.page-header').html() +  ' > ' + act);
					$('#modeaccordion').hide();

				break;
				
				


				case 'deploy':
				
						var html = '';
					if(data[0].error == 200)
						{
						 alert('Success...'+data[0].port);
						html +='<div class="final" >'+ '<h3>Thankyou</h3>'+
						'</div>'+'<br>';
						}
					
					else{
						 alert('Failure....');
							html +='<div class="final" >'+ '<h3>Thankyou </h3>'+
							'</div>'+'<br>';
							
					}
						$('#page-content').html(html);
						$('#modeaccordion').hide();
					
			
				break;
					
				}
				
			
					
				}
			else if(mode=="info")
			{
			
			switch(act)
			{
			case 'help':
				{
				$('#breadcrumbs').show();
				var html =  '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick="window.location.reload()" mode="software" action="oslist">Back</button><br/><br/>';
				
			  	html +='<div class="help" >'+ '<p>User Manual'+'<br>'+
				'<br>'+
				'Contents:'+'<br>'+
				'<br>'+
				'ESAMP-Endpoint Software/Application Management Portal is a web tool which provides two functionalities'+'<br>'+
				'-Software Installation'+'<br>'+
				'-Application Deployment'+'<br>'+
				'<br>'+
				'Compatibility: Linux(only)-fedora 19 onwards'+'<br>'+
				'<br>'+
				'1. Software Installation:'+'<br>'+
				'This feature automates the process of software installation. '+'<br>'+
				'You can select the software from the existing list or add a new software to the repository.'+'<br>'+
				'<br>'+
				'1.1.Working:'+'<br>'+
				'<br>'+
				'a)The Chef tool is used to automate the process of software installation.'+'<br>'+
				'<br>'+
				'b)Your machine will be bootstrapped and chef-client will be installed once you click SUBMIT.'+'<br>'+
				'<br>'+
				'c)For this reason you need to provide the root password and the IP address of the endpoint.'+'<br>'+
				 ' We ensure password integrity.'+'<br>'+
				 '<br>'+
				'd)The machine credentials will be enrolled with the Chef Server.'+'<br>'+
				'<br>'+
				'e)After the installation is completed, a pop up will appear saying "Software Installed!" or an error message will be displayed if any error  occurs.'+'<br>'+
				'<br>'+
				'<br>'+
				'2. Application Deployment:'+'<br>'+
				'This feature provides a development enviornment for a web application.'+'<br>'+
				'The enviornment types provided are- NodeJS & Tomcat.'+'<br>'+
				'<br>'+
				
				'Prerequisites- '+'<br>'+
				'1)Docker should be installed on client side.'+'<br>'+
				'2)Docker remote API should be enabled.'+'<br>'+
				'3)ssh should be enabled, firewall should be disabled.'+'<br>'+
				'<br>'+
				'2.1.Working:'+'<br>'+
				'<br>'+
				'a)Docker tool is used package an application and all its dependencies inside isolated and lightweight containers.'+'<br>'+
				'<br>'+
				'b)User will select the required enviornment and version, for example: Tomcat 7jre7.'+'<br>'+
				'<br>'+
				'c)User should provide the path of the file to be deployed. The file should be inside a "tar" archive. Or a war file in case of Tomcat.'+'<br>'+
				'<br>'+
				'd)The root password and the IP address of the endpoint needs to be provided for secure copying of file from client to server.'+'<br>'+
				 ' We ensure password integrity.'+'<br>'+
				 '<br>'+
				'e)Docker image will be built using a Dockerfile corresponding to the environment.Docker image will simply contain all the dependencies required for the web application to run.'+'<br>'+
				'<br>'+
				'f)Docker container will be created and started using the docker image and the file will be copied into the container.'+'<br>'+
				'<br>'+
				'g)User can then execute the web application using the port number of the started container.'+'<br>'+
				'<br>'+
					'</p>'+
				'</div>'+'<br>';
				$('#page-content').html(html);
				$('#modeaccordion').hide();
				$('.page-header').html(" ");
				break;
				}
				
			case 'about':
			{
				var html =  '<button type="submit" class="btn btn-default" style = "margin-left:15px;" onclick="window.location.reload()" mode="software" action="oslist">Back</button><br/><br/>';
				
			html +='<div class="help" >'+ '<p>Web tool developed by-'+'<br>'+'<br>'+

			'1.Tushar Dahibhate'+'<br>'+
			'2.Shruti kothari'+'<br>'+
			'3.Krish Gambhir'+'<br>'+
			'4.Sushant Dharmadhikari'+'<br>'+
			'<br>'+
			'For any assistance please mail us at-'+'<br>'+
			'ESAMPtool@gmail.com</p>'+'<br>'+
			'</div>'+'<br>';
			$('#page-content').html(html);
			$('#modeaccordion').hide();
			$('.page-header').html(" ");
		
		
			break;
			}
		
			}
			}
			
		});
	}
}
