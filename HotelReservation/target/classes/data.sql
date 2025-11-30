INSERT INTO HOTEL (nom, adresse) VALUES
('Hôtel Royal', '15 Avenue des Champs-Élysées, Paris'),
('Hôtel Méditerranée', '25 Boulevard de la Mer, Nice'),
('Hôtel Montagne', '8 Rue des Alpes, Chamonix');

INSERT INTO CHAMBRE (numero, type, prix, disponible, hotel_id) VALUES
('101', 'Standard', 80.0, true, 1),
('102', 'Standard', 80.0, true, 1),
('103', 'Deluxe', 120.0, true, 1),
('201', 'Suite', 200.0, true, 1),
('101', 'Standard', 75.0, true, 2),
('102', 'Vue Mer', 110.0, true, 2),
('103', 'Suite Vue Mer', 180.0, true, 2),
('101', 'Standard Montagne', 90.0, true, 3),
('102', 'Deluxe Montagne', 140.0, true, 3);
