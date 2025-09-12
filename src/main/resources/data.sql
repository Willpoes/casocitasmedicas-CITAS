INSERT INTO paciente (nombre, edad, historial) VALUES
('Juan Perez', 30, 'Hipertensión'),
('Maria Lopez', 25, 'Asma'),
('Carlos Ramirez', 40, 'Diabetes'),
('Ana Torres', 29, 'Ninguno'),
('Luis Fernandez', 35, 'Colesterol alto'),
('Carmen Sanchez', 42, 'Problemas cardíacos');

INSERT INTO cita_medica (hora_ingreso, hora_salida, medico, tipo_cita, centro_medico, paciente_id) VALUES
('2025-09-12T10:00:00', '2025-09-12T11:00:00', 'Dr. Ramírez', 'Consulta general', 'Clínica Central', 1),
('2025-09-13T09:00:00', '2025-09-13T10:00:00', 'Dra. Torres', 'Cardiología', 'Hospital Nacional', 2);
