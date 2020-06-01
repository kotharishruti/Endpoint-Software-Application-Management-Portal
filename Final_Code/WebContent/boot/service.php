<?php

$action = $_REQUEST['action'];
$mode   = $_REQUEST['mode'];

switch ($action) {
	case 'oslist' :
		$oslist = [
			['osid' => 1, 'osname' => 'RedHat',  'osimage' => ''],
			['osid' => 2, 'osname' => 'CentOS',  'osimage' => ''],
			['osid' => 3, 'osname' => 'Ubuntu',  'osimage' => ''],
			['osid' => 4, 'osname' => 'Windows', 'osimage' => ''],
			['osid' => 5, 'osname' => 'Feodra',  'osimage' => ''],
		];

		header("Access-Control-Allow-Origin: *");
		header("Content-Type: text/json");
		echo json_encode($oslist);
	break;

	case 'softwarelist' :
		$softwarelist = [
			['softwareid' => 1, 'softwarename' => 'IIS',    'softwareimage' => ''],
			['softwareid' => 2, 'softwarename' => 'Apache', 'softwareimage' => ''],
			['softwareid' => 3, 'softwarename' => 'Tomcat', 'softwareimage' => ''],
			['softwareid' => 4, 'softwarename' => 'JBOSS',  'softwareimage' => ''],
			['softwareid' => 5, 'softwarename' => 'Nginx',  'softwareimage' => ''],
		];

		header("Access-Control-Allow-Origin: *");
		header("Content-Type: text/json");
		echo json_encode($softwarelist);	
	break;

	case 'submitsofware' :
		$submitlist = ['mode' => $_REQUEST['mode'], 'osid' => 1, 'osname' => 'RedHat', 'osimage' => '', 'softwareid' => 1, 'softwarename' => 'IIS', 'softwareimage' => ''];

		header("Access-Control-Allow-Origin: *");
		header("Content-Type: text/json");
		echo json_encode($submitlist);	
	break;

}