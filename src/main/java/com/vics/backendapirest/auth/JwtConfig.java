package com.vics.backendapirest.auth;

public class JwtConfig {
	
	public static final String SECRET_KEY = "alguna.clave.secreta.full.dificil";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEAv0XLwGM57N0Mev1tqw2vM9bxfZXuhEne50HKXwA1WRUvUanE\n" + 
			"te/DXnObwGTHO5L60hQcgW0a8aQofpPq8SSfgHnovaCmiI/s9aoTtaQ0u6yzdnN9\n" + 
			"/ZW3MopH7DobYY+A5kZZ2LS0/KTzqAm1LuOc9iWmOowE7LOBVGoisg158DuRhmjV\n" + 
			"Rl7Zv0pBEKD+NK/kwdF7l7tJuwEVbI6fHOSsQhg34e8sJAwsqKZglIaTv4qMnCMI\n" + 
			"hQOl4aT2RABLVTcBxgMjnkri3ho9ID8XRSSsxIkRpw7CxjgzzxX8wmqV+Ib2bcvQ\n" + 
			"Hjn1Jf+Ofk/vPKAeQBWJQGV2N4+RA1voNr6KwQIDAQABAoIBABqIUQo/+XIM+8U3\n" + 
			"0HWw8UPMpjUYjjyVjrEfpDXnbn8L/ENkvF/MgZphiRJ4KFbwPZDUr1uYyesJLYDZ\n" + 
			"ffazcNi+kHV1ioRZCrLNQ+yneZUrEL32qVx63y3Ils+DA/dVk7jdu2rfLlf5fb8q\n" + 
			"kZKOimBuuo9WivxWrjMKggOW8PpM264zh2dglykUj9ehAda44zlPvr4UOmBmpe/L\n" + 
			"L+d/4kzMTCox0jzUx7LIUcZ/GD53FwGtqR9d/l6rrpAz/NcFdRyNUSqqouvcZbmz\n" + 
			"KDKyieqJ889JtoflTDWQh1vaZ7wYJeVwbVHT+behgMFCR5k2hrvaKrX/E3tibuRt\n" + 
			"L9xQ+JECgYEA4G1MGeOpe1Gcr/fpX04/fiFfqJ9tHbYxcaTYjYcsaYGQcDw3yYVf\n" + 
			"GaKw7QMOkLGa1CjDjjOLb23JK2vXOnMna3iXgjoj3SnLINmPSJ+/e7WVZiFfu6DY\n" + 
			"irR3iNSo2AabYvyhpicEPc5ujzzdixs4e4Cfe+akJZzZzfuQ7T0mA80CgYEA2i5z\n" + 
			"jzvZJQxWcVrszR4sw0RAJho14qxVIq77xcJxgEMIqv66x8eB1ZAcII7+kP3ndNiA\n" + 
			"/lSJJOPuyyhJltatDXIoI7cA/Xe6fr93kzuw7IsrBa7PceHhMTtgR0G1I1WGQe5o\n" + 
			"94ZAvUt6y97xc6koyOTGi4BlccN1omW0DN7SFsUCgYEA0WvMPsBdr5CJeP8Ky391\n" + 
			"6hn0bKJQLRCNdPGRjgecHtvjT4gxup/1DTPui0x18/l3dBCR4wkgTRuSRC1+irFY\n" + 
			"EIZYQxJD0yYjwYSimu9OvH7GaaYs8iX2yv/6JzniUKthpm393dC8uXv/WTQ/u4Xs\n" + 
			"H/r3V6PyepKx/++G3opYBeUCgYBicUcOv5AwWSq/pqjlu9GyoR7Ykkbx6ZPYPF+5\n" + 
			"UQxpNNW6YYjhDzGTrJwkTKCrxwQiAnXhSz899+IMpDbf6ijWwkU+KBo4Q0dQXQ+U\n" + 
			"zP/9GqXkwIU0+xfiYMoGHK2gLbyQDwQpetfHEUFsr0D+ULroOeEjnGEpPWncRktA\n" + 
			"62CbiQKBgQCbv7aSzymw+VK16NH148CCO8rDI74Cf31hMSvmaOahMolQszV1LEQr\n" + 
			"nTACfeOYye8WcVRdSf4kFCI5ayd/wcrbdZcgmZ7lSJH/5jyqtxtGtN521UZOcWX3\n" + 
			"D1c1EQA51G8S13uYAdP1/qED6/ewVTpZnOLrVBO6NTD5x4ffOT7Beg==\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv0XLwGM57N0Mev1tqw2v\n" + 
			"M9bxfZXuhEne50HKXwA1WRUvUanEte/DXnObwGTHO5L60hQcgW0a8aQofpPq8SSf\n" + 
			"gHnovaCmiI/s9aoTtaQ0u6yzdnN9/ZW3MopH7DobYY+A5kZZ2LS0/KTzqAm1LuOc\n" + 
			"9iWmOowE7LOBVGoisg158DuRhmjVRl7Zv0pBEKD+NK/kwdF7l7tJuwEVbI6fHOSs\n" + 
			"Qhg34e8sJAwsqKZglIaTv4qMnCMIhQOl4aT2RABLVTcBxgMjnkri3ho9ID8XRSSs\n" + 
			"xIkRpw7CxjgzzxX8wmqV+Ib2bcvQHjn1Jf+Ofk/vPKAeQBWJQGV2N4+RA1voNr6K\n" + 
			"wQIDAQAB\n" + 
			"-----END PUBLIC KEY-----"; 

}
