var needle = require("needle");
var os   = require("os");

var config = {};
config.token = " ";
config.ssh_key =  

var headers =
{
	'Content-Type':'application/json',
	Authorization: 'Bearer ' + config.token
};

// Documentation for needle:
// https://github.com/tomas/needle

var client =
{
	/*setkeys: function( onResponse )
    {
    var publickey =     
    {
    "name": "Key1",
    "publickey":
	}
    needle.post("https://api.digitalocean.com/v2/account/keys", publickey, {headers:headers, json:true}, onResponse) 
    },

    getkeys: function( onResponse )
    {
        needle.get("https://api.digitalocean.com/v2/account/keys", {headers:headers}, onResponse)
    },
*/
	listRegions: function( onResponse )
	{
		needle.get("https://api.digitalocean.com/v2/regions", {headers:headers}, onResponse)
	},
	listImages: function( onResponse )
	{
		needle.get("https://api.digitalocean.com/v2/images", {headers:headers}, onResponse)
	},

	createDroplet: function (dropletName, region, imageName, onResponse)
	{
		var data = 
		{
			"name": dropletName,
			"region":region,
			"size":"512mb",
			"image":imageName,
			// Id to ssh_keyskey already associated with account.
			"ssh_keys":[config.ssh_key],
			//"ssh_keys":null,
			"backups":false,
			"ipv6":false,
			"user_data":null,
			"private_networking":null
		};

		console.log("Attempting to create: "+ JSON.stringify(data) );

		needle.post("https://api.digitalocean.com/v2/droplets", data, {headers:headers,json:true}, onResponse );
	},

	
	 retrievedroplet: function(dropletId, onResponse)
  {
  	var address = "https://api.digitalocean.com/v2/droplets/" + dropletId ;
  	needle.get(address, {headers:headers}, onResponse)
  }

};


// #############################################
// #1 Print out a list of available regions
// Comment out when completed.
// https://developers.digitalocean.com/documentation/v2/#list-all-regions
// use 'slug' property
/*client.listRegions(function(error, response)
{
	var data = response.body;
	//console.log( JSON.stringify(response.body) );

	if( response.headers )
	{
		console.log( "Calls remaining", response.headers["ratelimit-remaining"] );
	}

	if( data.regions )
	{
		for(var i=0; i<data.regions.length; i++)
		{
			console.log("Data Regions", data.regions[i]); 
		}
	}
}); */


// #############################################
// #2 Extend the client object to have a listImages method
// Comment out when completed.
// https://developers.digitalocean.com/documentation/v2/#images
// - Print out a list of available system images, that are AVAILABLE in a specified region.
// - use 'slug' property
/*client.listImages(function(error, response)
{
	var data = response.body;
	//console.log( JSON.stringify(response.body) );

	if( response.headers )
	{
		console.log( "Calls remaining", response.headers["ratelimit-remaining"] );
	}

	if( data.images )
	{
		for(var i=0; i<data.images.length; i++)
		{if(data.images[i].public == true)
			{console.log("List Images", data.images[i]); 
		}
	}
	}
});
*/
// #############################################
// #3 Create an droplet with the specified name, region, and image
// Comment out when completed. ONLY RUN ONCE!!!!!
// Write down/copy droplet id.

/*client.setkeys(function(error, response)
    {
        console.log("In droplet list function");
        var data = response.body;
        console.log( JSON.stringify(response.body) );
        //dropletid = (data.droplets[0].id);
        //console.log( JSON.stringify() );
        console.log(data.ssh_key.id);
    });

    client.getkeys(function(error, response)
    {
        console.log("In getkeys list function");
        var data = response.body;
        console.log( JSON.stringify(response.body) );
        console.log(data.ssh_keys[0].id);
    });
*/
var name = ' ';
var region = 'nyc1'; // Fill one in from #1
var image = 'ubuntu-15-04-x64'; // Fill one in from #2
var dropletId = '';
client.createDroplet(name, region, image, function(err, resp, body)
{
    console.log(body);
    // StatusCode 202 - Means server accepted request.
    if(!err && resp.statusCode == 202)
    {
        dropletId = body.droplet.id;
        console.log( JSON.stringify( body, null, 3 ) );
    setTimeout(function()
    	{client.retrievedroplet(dropletId, function(error, response)
		{
	var data = response.body;
	if( response.headers )
			{	console.log( "Calls remaining", response.headers["ratelimit-remaining"] );
			}

	if (data.droplet)
			{	
		console.log("Image Id: " + data.droplet.id);
        console.log("Image Name: " + data.droplet.name);
		console.log ("Ip Address : " + data.droplet.networks.v4[0].ip_address);
		inventory = require('fs');
		inventory.writeFile('inventory', '[IpAddress] ' + data.droplet.networks.v4[0].ip_address, function (err) {
  			if (err) throw err;
  				console.log('Saved!');
				});
	
			}
		});

	},20000);	
    }
});

// #############################################
// #4 Extend the client to retrieve information about a specified droplet.
// Comment out when done.
// https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-droplet-by-id
// REMEMBER POST != GET
// Most importantly, print out IP address!

/*client.retrievedroplet(dropletId, function(error, response)
{
	var data = response.body;
	if( response.headers )
	{
		console.log( "Calls remaining", response.headers["ratelimit-remaining"] );
	}

	if (data.droplet)
	{	
		console.log("Image Id: " + data.droplet.id);
        console.log("Image Name: " + data.droplet.name);
		console.log ("Ip Address : " + data.droplet.networks.v4[0].ip_address);
	
	}
});
*/
// #######################	######################
// #5 In the command line, ping your server, make sure it is alive!
// 	ping xxx.xx.xx.xx;

// #############################################
// #6 Extend the client to DESTROY the specified droplet.
// Comment out when done.
// https://developers.digitalocean.com/documentation/v2/#delete-a-droplet
// HINT, use the DELETE verb.
// HINT #2, needle.delete(url, data, options, callback), data needs passed as null.
// No response body will be sent back, but the response code will indicate success.
// Specifically, the response code will be a 204, which means that the action was successful with no returned body data.
// 	if(!err && resp.statusCode == 204)
// 	{
//			console.log("Deleted!");
// 	}

// #############################################
// #7 In the command line, ping your server, make sure it is dead!
// ping xx.xx.xx.xx
// It could be possible that digitalocean reallocated your IP address to another server, so don't fret it is still pinging.
