# language: fr
Fonctionnalité: Gestion du Timer et du Stopwatch
  En tant qu'utilisateur du système d'horloge
  Je veux pouvoir utiliser le minuteur et le chronomètre
  Afin de mesurer le temps efficacement

  Scénario: Réglage et lancement du minuteur
    Etant donné que je suis en mode "TIMER"
    Et que le minuteur est à l'état "IdleTimer"
    Quand j'appuie sur le bouton "RIGHT"
    Et j'attends un "tick"
    Alors l'état doit être "SetTimer"
    Et la mémoire doit être à 1
    Quand j'appuie sur le bouton "UP"
    Alors la mémoire doit être à 6
    Quand j'appuie sur le bouton "RIGHT"
    Et j'attends un "tick"
    Alors l'état doit être "IdleTimer"
    Et la mémoire doit être à 6
    Quand j'appuie sur le bouton "UP"
    Alors l'état doit être "RunningTimer"
    Et le temps restant doit être de 6 secondes

  Scénario: Utilisation du chronomètre avec temps intermédiaire
    Etant donné que je suis en mode "TIMER"
    Quand j'appuie sur le bouton "LEFT"
    Alors l'état doit être "ResetStopwatch"
    Et le mode doit être "stopwatch"
    Quand j'appuie sur le bouton "UP"
    Et j'attends un "tick"
    Alors l'état doit être "RunningStopwatch"
    Et le temps total doit être de 1 seconde
    Quand j'appuie sur le bouton "UP"
    Et j'attends un "tick"
    Alors l'état doit être "LaptimeStopwatch"
    Et le temps intermédiaire doit être de 1 seconde
    Et le temps total doit être de 2 secondes
