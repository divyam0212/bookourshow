var emp = {
		"employees" : [ {
			"empno" : 1234,
			"empname" : "ABC",
			"empsalary" : 120000,
			"department" : {
				"deptno" : 10,
				"deptname" : "HR",
				"deptlocation" : "CHENNAI"
			},
			"address" : {
				"addressid" : 1,
				"street" : "THORAIPAKKAM",
				"city" : "CHENNAI",
				"state" : "TAMILNADU",
				"country" : "INDIA"
			},
			"skills" : [ {
				"skill" : {
					"skillid" : 1,
					"skillname" : "JAVA"
				},
				"skill" : {
					"skillid" : 2,
					"skillname" : "SPRING"
				}
			} ]

		} ]
	}

var str=JSON.stringify(emp["employees"][0]["address"]);
alert(emp["employees"][0]["address"]["street"]);

function validation() {
	var uname = document.forms["login"]["username"].value;
	var pass = document.forms["login"]["password"].value;

	if (uname == "" || pass == "") {
		alert("UserName and Password must be Filled");
	}
	var regex = /^[0-4]{4}$/;
	var regex2 = /^[0-4]{4}[a-z]{4}$/;
	/*
	 * var regex2=
	 * /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;
	 */

	if (uname.match(regex)) {
		alert("Username is 4 digits");
	} else {
		alert("Username is wrong digits");
		return false;
	}
	if (uname.match(regex) && pass.match(regex2)) {
		alert("Password is 4 digits and 4 characters");
		return true
	} else {
		alert("Password is wrong");
		return false;
	}
}
function print1() {
	var p1 = document.getElementById("p1");
	p1.innerHTML = "<b>I AM FINE</b>"
			+ "<form name='fone'>"
			+ "<input type='text' name='username' placeholder='enter username'><br>"
			+ "<input type='password' name='pass' placeholder='enter password'><br>"
			+ "<input type='submit' name='submit' value='LOGIN'><br>";
}
