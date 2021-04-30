# Teaching-HEIGVD-RES-2021-Labo-SMTP

Client de "prank" SMTP développé dans le cadre du cour RES 2021 de la HEIG-VD.

Cette application permet de créer et d'envoyer des mails forgés.



## SMTP mock serveur

<mark> TODO </mark>



## Utilisation

### Configuration de la connexion

Fichier `config/config.properties`

````bash
# Adresse du serveur SMTP
smtpServerAddress=localhost

# Port du serveur SMTP
smtpServerPort=25

# Nombre de groupes de personnes à créer
numberOfGroups=1

# Le témoin du prank
witnessesToCC=me@mail.com
````



### Configuration des messages de prank

Fichier `config/messages.utf-8`

Les messages doivent commencer par la ligne `Subject: <sujet du mail>` qui défini le sujet du mail.

Les messages sont séparés par `==`

**Exemple**:

````bash
Subject: sujet1
Mon message
sur plusieurs lignes
==
Subject: sujet2
Mon message sur une ligne
````



### Configuration de la liste des "victimes"

Fichier `config/victims.utf8`

Ce fichier contient les adresses mail des "victimes" du prank, avec une adresse par ligne.

````bash
mail_1@mail.com
mail_2@mail.com
````



### Envoi des mails

Les commandes ci-dessous doivent être faites depuis le dossier `Client_SMTP_Prank`.

````bash
# Construction du fichier jar
mvn clean install

# Lancement de l'application
java -jar target/client_smtp_prank-1.0-SNAPSHOT.jar
````



## Implémentation

UML

![](figures/UML.svg)







<mark>TODO</mark>

* **A brief description of your project**: if people exploring GitHub find your repo, without a prior knowledge of the RES course, they should be able to understand what your repo is all about and whether they should look at it more closely.
* **Instructions for setting up a mock SMTP server (with Docker - which you will learn all about in the next 2 weeks)**. The user who wants to experiment with your tool but does not really want to send pranks immediately should be able to use a mock SMTP server. For people who are not familiar with this concept, explain it to them in simple terms. Explain which mock server you have used and how you have set it up.
* **Clear and simple instructions for configuring your tool and running a prank campaign**. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.
* **A description of your implementation**: document the key aspects of your code. It is probably a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).
