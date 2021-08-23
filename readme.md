readme : SoundSfire    Oudard-Riss

Reprise du projet Dev Mobile depuis le point

Thomas O.
* Ajout et correctif des listeners entre les fragments 
* Ajout des aides en commentaires sur certaines classes permettant de mieux retrouver les informations
* J'ai decidé d'utiliser un design patern builder pour les audio files. Cela permet de ne pas créer plusieur contructeur et de laisser le constructeur audioFile en pricate






Maxime R.
* Ajout de la fonction de lecture de musique depuis ViewHolder.onClick en utilisant l'api MediaPlayer,
* on y prépare la musique et on récupère le chemin du fichier pour le jouer. Il est important de stopper
* le mediaplayer à chaque click sinon les musiques se superposent et le tout se transforme en brouhaha

* N'ayant pas de Samsung disponible sous la main, nous n'avons pas pu tester sur un vrai appareil. 
* Cependant les résultats sur VM ont été satisfaisant.


