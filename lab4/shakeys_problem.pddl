(define (problem shakeys-problem)
  (:domain shakeys-world)
  (:objects
   room1 room2 room3 - room
   b - box
   rubber-duck ball - small-object)
  (:init
   (wide-door room1 room2) (wide-door room2 room1)
   (wide-door room2 room3) (wide-door room3 room2)
   (door room2 room3) (door room3 room2)
   (shakey-in-room room2)
   (box-in-room b room1)
   (object-in-room rubber-duck room1)
   (object-in-room ball room2))
  (:goal (forall (?o - small-object) (object-in-room ?o room3)))
  )
