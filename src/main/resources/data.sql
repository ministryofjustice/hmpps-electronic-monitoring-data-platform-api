TRUNCATE TABLE device_wearer;

INSERT INTO device_wearer (id, device_wearer_id, first_name, last_name, "type")
VALUES (1, '3fc55bb7-ba52-4854-be96-661f710328fc', 'John', 'Smith', 'Historical Case Centric'),
       (2, 'ba34370c-9bf7-44eb-943e-2a6566205f99', 'Jane', 'Doe', 'Historical User Centric'),
       (3, 'cdfd3e77-0015-40bf-b9b7-0b608a2826ca', 'Michael', 'Jordan', 'Historical Case Centric')
;

TRUNCATE TABLE gps_data;

INSERT INTO gps_data (id, device_wearer_id, latitude, location_Time, longitude)
VALUES (1, '3fc55bb7-ba52-4854-be96-661f710328fc', 20.0, '2000-10-31T01:30:00.000-05:00', 21.0),
       (2, 'ba34370c-9bf7-44eb-943e-2a6566205f99', 25.0, '2000-10-31T01:31:00.000-05:00', 26.0),
       (3, 'cdfd3e77-0015-40bf-b9b7-0b608a2826ca', 30.0, '2000-10-31T01:32:00.000-05:00', 31.0)
;