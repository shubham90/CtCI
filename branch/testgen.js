var esprima = require("esprima");
var options = {tokens:true, tolerant: true, loc: true, range: true };
var faker = require("faker");
var fs = require("fs");
faker.locale = "en";
var mock = require('mock-fs');
var _ = require('underscore');
var ListIterator = require('list-iterator');
var Random = require('random-js');
var combArr = [];


function main() 
{
    var args = process.argv.slice(2);

    if (args.length == 0) {
        args = ["subject.js"];
    }
    var filePath = args[0];
    constraints(filePath);
	   generateTestCases()

}

var engine = Random.engines.mt19937().autoSeed();

function createConcreteIntegerValue(greaterThan, constraintValue) 
{
    if (greaterThan)
        return Random.integer(constraintValue, constraintValue + 10)(engine);
    else
        return Random.integer(constraintValue - 10, constraintValue)(engine);
}

function fakeDemo() 
{
    console.log(faker.phone.phoneNumber());
    console.log(faker.phone.phoneNumberFormat());
    console.log(faker.phone.phoneFormats());
}

var functionConstraints = {}

function Constraint(properties) 
{
    this.ident = properties.ident;
    this.expression = properties.expression;
    this.operator = properties.operator;
    this.value = properties.value;
    this.funcName = properties.funcName;
    this.mocking = properties.mocking;
}

var mockFileLibrary = {
    pathExists: {
        'path/fileExists': {},
        'path/filePresent': {" ": " "}
    },
    fileWithContent: {
        pathContent: {file1: 'file',file2: 'file content',}
    },

    fileWithoutContent: {
        pathContent: {file1: 'file',}
    },

    fileNotExists: {
        pathContent: {file2: '',}
    }
};


var phoneOptions = [{'normalize': true}, {'shouldNormalize': true}];

function generateTestCases() {

    var content = "var subject = require('./subject.js')\nvar mock = require('mock-fs');\n";
    for (var funcName in functionConstraints) {
        var params = {};
        for (var i = 0; i < functionConstraints[funcName].params.length; i++) {
            var paramName = functionConstraints[funcName].params[i];
            params[paramName] = [];
        }
       
        var constraints = functionConstraints[funcName].constraints;
        var pathExists = _.some(constraints, {mocking: 'fileExists'});
        var fileWithContent = _.some(constraints, {mocking: 'fileWithContent'});
        var fileWithoutContent = _.some(constraints, {mocking: 'fileWithoutContent'});
        var fileNotExists = _.some(constraints, {mocking: 'fileNotExists'});
        var phoneNum = _.contains(functionConstraints[funcName].params, "phoneNumber");

        for (var c = 0; c < constraints.length; c++) {
            var constraint = constraints[c];
            if (params.hasOwnProperty(constraint.ident)) {
                params[constraint.ident].push(constraint.value);
            }
      
        }
        combArr = rand(params);    
        for (var i = 0; i < combArr.length; i++) 
        {   var args = [];
            args = combArr[i];
            content += "subject.{0}({1});\n".format(funcName, args);
        }
      
        if (pathExists || fileWithContent  || fileNotExists) 
        { for (var i = 0; i < combArr.length; i++) 
        	{   var args = [];
                args = combArr[i];

            content += generateMockFsTestCases(pathExists,!fileWithContent,!fileWithoutContent,!fileNotExists,funcName, args);
			// Bonus...generate constraint variations test cases....
			content += generateMockFsTestCases(pathExists,fileWithContent,!fileWithoutContent, !fileNotExists, funcName, args);
			content += generateMockFsTestCases(pathExists,!fileWithContent, fileWithoutContent, !fileNotExists ,funcName, args);
			content += generateMockFsTestCases(pathExists,fileWithContent, !fileWithoutContent, fileNotExists,funcName, args);
			content += generateMockFsTestCases(!pathExists,!fileWithContent, !fileWithoutContent, !fileNotExists,funcName, args);
			content += generateMockFsTestCases(!pathExists,!fileWithContent,!fileWithoutContent,!fileNotExists,funcName, args);
			content += generateMockFsTestCases(!pathExists,fileWithContent,!fileWithoutContent, !fileNotExists, funcName, args);
			content += generateMockFsTestCases(!pathExists,!fileWithContent, fileWithoutContent, !fileNotExists ,funcName, args);
			content += generateMockFsTestCases(!pathExists,fileWithContent, !fileWithoutContent, fileNotExists,funcName, args);
			content += generateMockFsTestCases(pathExists,!fileWithContent, !fileWithoutContent, !fileNotExists,funcName, args);
            }

        } else {
            // Emit simple test case.
             content += "subject.{0}({1});\n".format(funcName, args);
        }

        if (phoneNum) {
            for (var x = 0; x < phoneOptions.length; x++) {
                args = '';
                var option = [];
                option = phoneOptions[x];
                args += "'" + faker.phone.phoneNumber() + "','" + faker.phone.phoneFormats() + "'," + JSON.stringify(option);
                content += "subject.{0}({1});\n".format(funcName, args);
            }

        }
        if(phoneNum)
		{
			content += genPhone("1111111111", "(NNN) NNN-NNNN", "", funcName, "" );
			content += genPhone("2222222222", "(NNN) NNN-NNNN", '{"normalize": true}', funcName, "" );
			content += genPhone(faker.phone.phoneNumber(), faker.phone.phoneFormats(), "", funcName, "");
		}

	}
	content += "subject.{0}({1});\n".format('blackListNumber', "'2121111111'");
    fs.writeFileSync('test.js', content, "utf8");

}

function generateMockFsTestCases(pathExists, fileWithContent, fileWithoutContent, fileNotExists, funcName, args) {
    var testCase = "";
    // Build mock file system based on constraints.
    var mergedFS = {};
    if (pathExists) {
        for (var attrname in mockFileLibrary.pathExists) {mergedFS[attrname] = mockFileLibrary.pathExists[attrname];
  fileWithContent      }
    }
    if (fileWithContent) {
        for (var attrname in mockFileLibrary.fileWithContent) {mergedFS[attrname] = mockFileLibrary.fileWithContent[attrname];
        }
    }

    if (fileWithoutContent) {
        for (var attrname in mockFileLibrary.fileWithoutContent) {mergedFS[attrname] = mockFileLibrary.fileWithoutContent[attrname];
        }
    }

    if(fileNotExists){
        for (var attrname in mockFileLibrary.fileNotExists) {mergedFS[attrname] = mockFileLibrary.fileNotExists[attrname];
        }
    }


    testCase +=
        "mock(" +
        JSON.stringify(mergedFS) +
        ");\n";

    testCase += "\tsubject.{0}({1});\n".format(funcName, args);
    testCase += "mock.restore();\n";
    return testCase;
}

function genPhone(phoneNumber, phoneNumberFormat, options, funcName, args)
{
	if(options == '')
			args+="'"+phoneNumber+"','"+phoneNumberFormat+"','"+options+"'";
		else
			args+="'"+phoneNumber+"','"+phoneNumberFormat+"',"+options;
	var testCase = "subject.{0}({1});\n".format(funcName, args );
	return testCase;
}

function constraints(filePath) {
    var buf = fs.readFileSync(filePath, "utf8");
    var result = esprima.parse(buf, options);

    traverse(result, function(node) {
        if (node.type === 'FunctionDeclaration') {
            var funcName = functionName(node);
            console.log("Line : {0} Function: {1}".format(node.loc.start.line, funcName));

            var params = node.params.map(function(p) {
                return p.name
            });

            functionConstraints[funcName] = {
                constraints: [],
                params: params
            };

            traverse(node, function(child) {
                if (child.type === 'BinaryExpression' && child.operator == "==") {
                    if (child.left.type == 'Identifier' && params.indexOf(child.left.name) > -1) {
                        var expression = buf.substring(child.range[0], child.range[1]);
                        var rightHand = buf.substring(child.right.range[0], child.right.range[1]);

                        functionConstraints[funcName].constraints.push(
                            new Constraint({
                                ident: child.left.name,
                                value: rightHand,
                                funcName: funcName,
                                mocking: "integer",
                                operator: child.operator,
                                expression: expression
                            }));
                        if (rightHand == undefined)
                        {
                            functionConstraints[funcName].constraints.push(
                                new Constraint({
                                    ident: child.left.name,
                                    value: ' ',
                                    funcName: funcName,
                                    mocking: "integer",
                                    operator: child.operator,
                                    expression: expression
                                }));
                        }
                        else if (rightHand != undefined) {
                            rightHand = parseInt(rightHand);
                            if (isNaN(rightHand)) {
                                functionConstraints[funcName].constraints.push(
                                    new Constraint({
                                        ident: child.left.name,
                                        value: '"randomText"',
                                        funcName: funcName,
                                        mocking: "string",
                                        operator: child.operator,
                                        expression: expression
                                    }));
                            } else {
                                functionConstraints[funcName].constraints.push(
                                    new Constraint({
                                        ident: child.left.name,
                                        value: 1,
                                        funcName: funcName,
                                        mocking: "integer",
                                        operator: child.operator,
                                        expression: expression
                                    }));
                            }
                        } 

                    }

                 }

                if (child.type === 'BinaryExpression' && child.operator == "<") {
                    if (child.left.type == 'Identifier' && params.indexOf(child.left.name) > -1) {
                        // get expression from original source code:
                        var expression = buf.substring(child.range[0], child.range[1]);
                        var rightHand = parseInt(buf.substring(child.right.range[0], child.right.range[1]));

                        functionConstraints[funcName].constraints.push(
                            new Constraint({
                                ident: child.left.name,
                                value: rightHand + 1,
                                funcName: funcName,
                                mocking: "integer",
                                operator: child.operator,
                                expression: expression
                            }),
                            new Constraint({
                                ident: child.left.name,
                                value: rightHand - 1,
                                funcName: funcName,
                                mocking: "integer",
                                operator: child.operator,
                                expression: expression
                            }));


                    }
                }

                if (child.type === 'BinaryExpression' && child.operator == ">") {
                    if (child.left.type == 'Identifier' && params.indexOf(child.left.name) > -1) {
                        // get expression from original source code:
                        var expression = buf.substring(child.range[0], child.range[1]);
                        var rightHand = parseInt(buf.substring(child.right.range[0], child.right.range[1]));

                        functionConstraints[funcName].constraints.push(
                            new Constraint({
                                ident: child.left.name,
                                value: rightHand + 1,
                                funcName: funcName,
                                mocking: "integer",
                                operator: child.operator,
                                expression: expression
                            }),
                            new Constraint({
                                ident: child.left.name,
                                value: rightHand - 1,
                                funcName: funcName,
                                mocking: "integer",
                                operator: child.operator,
                                expression: expression
                            }));


                    }
                }

               if (child.type === 'BinaryExpression' && child.operator == "!=") {
                    if (child.left.type == 'Identifier' && params.indexOf(child.left.name) > -1) {
                        var expression = buf.substring(child.range[0], child.range[1]);
                        var rightHand = buf.substring(child.right.range[0], child.right.range[1]);

                        functionConstraints[funcName].constraints.push(
                            new Constraint({
                                ident: child.left.name,
                                value: rightHand,
                                funcName: funcName,
                                mocking: "integer",
                                operator: child.operator,
                                expression: expression
                            }));
                        if (rightHand == undefined)
                        {
                            functionConstraints[funcName].constraints.push(
                                new Constraint({
                                    ident: child.left.name,
                                    value: ' ',
                                    funcName: funcName,
                                    mocking: "integer",
                                    operator: child.operator,
                                    expression: expression
                                }));
                        }
                        else if (rightHand != undefined) {
                            rightHand = parseInt(rightHand);
                            if (isNaN(rightHand)) {
                                functionConstraints[funcName].constraints.push(
                                    new Constraint({
                                        ident: child.left.name,
                                        value: '"randomText"',
                                        funcName: funcName,
                                        mocking: "string",
                                        operator: child.operator,
                                        expression: expression
                                    }));
                            } else {
                                functionConstraints[funcName].constraints.push(
                                    new Constraint({
                                        ident: child.left.name,
                                        value: 1,
                                        funcName: funcName,
                                        mocking: "integer",
                                        operator: child.operator,
                                        expression: expression
                                    }));
                            }
                        } 

                    }

                 }
                 if (child.type == "CallExpression" && child.callee.property && child.callee.property.name == "readFileSync") 
                 {
                     for (var p = 0; p < params.length; p++) 
                     {
                        if (child.arguments[0].name == params[p]) 
                        {
                            functionConstraints[funcName].constraints.push(
                                new Constraint({
                                    ident: params[p],
                                    value: "'pathContent/file1'",
                                    funcName: funcName,
                                    mocking: "fileWithContent",
                                    operator: child.operator,
                                    expression: expression
                                }),
                                new Constraint({
                                    ident: params[p],
                                    value: "'pathContent/file2'",
                                    funcName: funcName,
                                    mocking: "fileWithContent",
                                    operator: child.operator,
                                    expression: expression
                                }));
                        }
                    }
                }

                if (child.type == "CallExpression" &&  child.callee.property && child.callee.property.name == "readdirSync") 
                {
                    for (var p = 0; p < params.length; p++) 
                    {
                        if (child.arguments[0].name == params[p]) 
                        {
                            functionConstraints[funcName].constraints.push(
                                new Constraint({
                                    ident: params[0],
                                    value: "'path/filePresent'",
                                    funcName: funcName,
                                    mocking: "fileWithContent",
                                    operator: child.operator,
                                    expression: expression
                                }),
                                new Constraint({
                                    ident: params[0],
                                    value: "'path/fileExists'",
                                    funcName: funcName,
                                    mocking: "fileWithContent",
                                    operator: child.operator,
                                    expression: expression
                                }));
                        }
                    }
                }

                if (child.type == "CallExpression" && child.callee.property && child.callee.property.name == "existsSync") 
                {
                    for (var p = 0; p < params.length; p++) 
                    {
                        if (child.arguments[0].name == params[p]) 
                        {
                            functionConstraints[funcName].constraints.push(
                                new Constraint({
                                    ident: params[0],
                                    value: "'path/fileExists'",
                                    funcName: funcName,
                                    mocking: "fileExists",
                                    operator: child.operator,
                                    expression: expression
                                }));
                        }
                    }
                }



            });

            console.log(functionConstraints[funcName]);
        }
    });
}

function traverse(object, visitor) 
{   var key, child;
    visitor.call(null, object);
    for (key in object) 
    {
        if (object.hasOwnProperty(key)) 
        {
            child = object[key];
            if (typeof child === 'object' && child !== null) 
            {
                traverse(child, visitor);
            }
        }
    }
}

function traverseWithCancel(object, visitor) 
{   var key, child;
    if (visitor.call(null, object)) 
    {
        for (key in object) 
        {
            if (object.hasOwnProperty(key)) 
            {
                child = object[key];
                if (typeof child === 'object' && child !== null) 
                {
                    traverseWithCancel(child, visitor);
                }
            }
        }
    }
}

function rand(map) {
    var res = [];
    var current = new Object();
    genitev(map, new ListIterator(Object.keys(map)), current, res);
    return res;
}

function functionName(node) {
    if (node.id) {
        return node.id.name;
    }
    return "";
}


if (!String.prototype.format) {
    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    };
}

function genitev(map, itr, pre, res) 
{
    if (!itr.hasNext()) 
    {
        var arr = [];
        var keys = Object.keys(pre)
        for (var v in keys) 
        {
            arr.push(pre[keys[v]]);
        }
        res.push(arr);
    } 
    else 
    {
        var v = itr.next();
        var maparr = map[v];

        for (var index in maparr) 
        {
            pre[v] = maparr[index];
            genitev(map, itr, pre, res);
        }

        itr.previous();
    }
}

main();