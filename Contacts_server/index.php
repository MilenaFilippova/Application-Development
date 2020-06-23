<?php
$pdo = new PDO('mysql:host=localhost;dbname=test', 'root', '');
$pdo->exec("set names utf8");

if(isset($_GET['action'])){
	switch ($_GET['action']) {
		case 'add':
			$sql = $pdo->prepare('INSERT INTO `contacts` (`first_name`, `second_name`, `phone`) VALUES (:first_name, :second_name, :phone)');
			$sql->execute([
				':first_name' => $_GET['first_name'],
				':second_name' => $_GET['second_name'],
				':phone' => $_GET['phone']
			]);
			echo '{"answer": "ok"}';
			break;
		case 'update':
			$sql = $pdo->prepare('UPDATE `contacts` SET `first_name` = :first_name , `second_name` = :second_name, `phone` = :phone WHERE `id` = :id LIMIT 1');
			$sql->execute([
		        ':id' => $_GET['id'],
		        ':first_name' => $_GET['first_name'],
		        ':second_name' => $_GET['second_name'],
		        ':phone' => $_GET['phone']
		      ]);
			echo '{"answer": "ok"}';
			break;
		case 'getcontact':
			$sql = $pdo->prepare("SELECT * FROM `contacts` WHERE `id` = :id");
			$sql->execute([':id' => $_GET['id']]);
			$note = $sql->fetch(PDO::FETCH_ASSOC);
			echo "{\"answer\":\"ok\", \"data\":[\"id\":\"" . $note["ID"] . "\", \"first_name\":\"" . $note["FIRST_NAME"] . "\", \"second_name\":\"" . $note["SECOND_NAME"] . "\", \"phone\":\"" . $note["PHONE"] . "\"]}";
			break;
		case 'getlist':
			$contacts = $pdo->query('SELECT `id`, `first_name`, `second_name`, `phone` from `contacts`');
			echo '<table border="1" cellspacing="0">';
			echo '<tr><th>id</th><th>first_name</th><th>second_name</th><th>phone</th></tr>';
			foreach ($contacts as $contact)
		    {
		    	echo '<tr><td>' . $contact["id"] . "</td><td>" . $contact["first_name"] . "</td><td>" . $contact["second_name"] . "</td><td>" . $contact["phone"] . "</td></tr>";

		    }
		    echo '</table>';
			break;
		case 'delete':
			$sql = $pdo->prepare('DELETE FROM `contacts` WHERE `id` = :id LIMIT 1');
      		$sql->execute([':id' => $_GET['id']]);
      		echo '{"answer": "ok"}';
			break;
	}
}
?>

