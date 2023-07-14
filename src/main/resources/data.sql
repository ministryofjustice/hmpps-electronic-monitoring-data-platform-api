
TRUNCATE TABLE device_wearer, device, location;

INSERT INTO device_wearer (id, device_wearer_id, first_name, last_name, type)
VALUES (1, '3fc55bb7-ba52-4854-be96-661f710328fc', 'John', 'Smith', 'Historical Case Centric'),
       (2, 'ba34370c-9bf7-44eb-943e-2a6566205f99', 'Jane', 'Doe', 'Historical User Centric'),
       (3, 'cdfd3e77-0015-40bf-b9b7-0b608a2826ca', 'Michael', 'Jordan', 'Historical Case Centric')
;

INSERT INTO device (id, device_id, model_id, firmware_version, device_type, status, battery_life_remaining, date_tag_fitted, date_tag_removed, device_wearer_id)
VALUES (1, 'd064e527-284d-4fc0-bda7-6295f1f7c8f4', '4fbda134-1bee-11ee-be56-0242ac120002', '1.0', 'GPS', 'OK', 80, '2000-10-30T01:32:00.000-00:00', NULL, 1)
;

INSERT INTO location (id, device_id, latitude, location_Time, longitude)
VALUES (1, 1, 20.0, '2000-10-31T01:30:00.000-00:00', 21.0),
       (2, 1, 21.0, '2000-10-31T03:30:00.000-00:00', 20.0),
       (3, 1, 28.0, '2000-10-31T08:31:00.000-00:00', 29.0),
       (4, 1, 30.0, '2000-11-12T03:32:00.000-00:00', 31.0),
       (5, 1, 22.0, '2000-11-14T03:30:00.000-00:00', 34.0),
       (6, 1, 26.0, '2000-11-15T02:30:00.000-00:00', 37.0)
