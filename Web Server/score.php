
<?php

$dom = new DOMDocument("1.0","ISO-8859-15");
$dom->preserveWhiteSpace = false;
$dom->formatOutput = true;
$dom->loadXML(file_get_contents("score_database.xml"));
$root = $dom->documentElement;

$player =  $dom->createElement('PLAYER');
$score = $dom->createElement('SCORE');
$name = $dom->createElement('NAME');

$hacked=false;
$strscore=$_GET['khuvg'];

if(strlen($strscore)>19 || strlen($strscore)<17){
$hacked=true;
}
if($hacked==false && strcmp(substr($strscore,0,8),"74108520")!=0 ){
$hacked=true; 
}
if($hacked == false && strcmp(substr($strscore,-8),"74123698")!=0){
$hacked=true;
}

$realscore;

if($hacked==false && strlen($strscore)==17){
$realscore=substr($strscore,8,1);
$score->nodeValue=$realscore;
}
else if ($hacked == false && strlen($strscore)==18){
$realscore=substr($strscore,8,2);
$score->nodeValue=$realscore;
}
else if ($hacked == false && strlen($strscore)==19){
$realscore=substr($strscore,8,3);
$score->nodeValue=$realscore;
}
if (strcmp(substr($realscore,0,1),"0")==0){
$hacked=true;
}
$name->nodeValue= $_GET['livwh'];

if($hacked==false)
{
$player->appendChild($name);
$player->appendChild($score);

$playerRef;
foreach($root->getElementsByTagName('PLAYER') as $person){
	if(strlen($player->getElementsByTagName('SCORE')[0]->nodeValue) < strlen($person->getElementsByTagName('SCORE')[0]->nodeValue))
	{
		continue;
	}
	if(strlen($player->getElementsByTagName('SCORE')[0]->nodeValue)>strlen($person->getElementsByTagName('SCORE')[0]->nodeValue))
	{
		$playerRef = $person;break;
	}

	if(strcmp($player->getElementsByTagName('SCORE')[0]->nodeValue,$person->getElementsByTagName('SCORE')[0]->nodeValue) >=0)
	{
		$playerRef = $person;break;
	}
}


$duplicate=false;

foreach($root->getElementsByTagName('PLAYER') as $person){
	if(strcmp($player->getElementsByTagName('NAME')[0]->nodeValue,$person->getElementsByTagName('NAME')[0]->nodeValue) == 0 &&
	strcmp($player->getElementsByTagName('SCORE')[0]->nodeValue,$person->getElementsByTagName('SCORE')[0]->nodeValue) == 0)
	{
		$duplicate=true; break;
	}
}
if($duplicate==false){
    $root->insertBefore($player,$playerRef);
}

}


echo "[Top 10 High Score Chart]<br>";
$counter;
foreach($root->getElementsByTagName('PLAYER') as $person){
	$output = $person->getElementsByTagName('NAME')[0]->nodeValue.": ".$person->getElementsByTagName('SCORE')[0]->nodeValue;
	echo $output."<br>";
	$counter=$counter+1;
	if($counter==10){break;}
}
$dom->save("score_database.xml");
echo "<br><br><br>Reminder: close and reopen browser to play again."

?>
