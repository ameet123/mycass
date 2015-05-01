package org.ameet.rts.cassandra.apimodel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ameet.rts.cassandra.util.Utility;

import com.google.common.base.Strings;

public class RTSModel {

	private RTSMessage rtsMessage;

	public RTSMessage getRtsMessage() {
		return rtsMessage;
	}

	public void setRtsMessage(RTSMessage rtsMessage) {
		this.rtsMessage = rtsMessage;
	}

	public static class RTSMessage {
		private Header header;
		private Body body;

		public Body getBody() {
			return body;
		}

		public void setBody(Body body) {
			this.body = body;
		}

		public Header getHeader() {
			return header;
		}

		public void setHeader(Header header) {
			this.header = header;
		}

	}

	public static class Header {
		private String correlationId;
		private String objectKlass;
		private String operation;
		private Map<String, String> parameters;

		public String getCorrelationId() {
			return correlationId;
		}

		public void setCorrelationId(String correlationId) {
			this.correlationId = correlationId;
		}

		public String getObjectKlass() {
			return objectKlass;
		}

		public void setObjectKlass(String objectKlass) {
			this.objectKlass = objectKlass;
		}

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

		public Map<String, String> getParameters() {
			return parameters;
		}

		public void setParameters(Map<String, String> parameters) {
			this.parameters = parameters;
		}
	}

	public static class Body {
		private WellModel well;

		public WellModel getWell() {
			return well;
		}

		public void setWell(WellModel well) {
			this.well = well;
		}

		public static class WellModel {
			private String name;
			private String nameLegal;
			private String numGovt;
			private String field;
			private String country;
			private String state;
			private String county;
			private String block;
			private String timeZone;
			private String operator;
			private String numAPI;
			private String dTimSpud;
			private List<WellDatum> wellDatum;
			private GroundElevation groundElevation;
			private WaterDepth waterDepth;
			private List<WellLocation> wellLocation;
			private CommonData commonData;
			private String uid;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getNameLegal() {
				return nameLegal;
			}

			public void setNameLegal(String nameLegal) {
				this.nameLegal = nameLegal;
			}

			public String getNumGovt() {
				if (Strings.isNullOrEmpty(numGovt)) {
					numGovt = "";
				}
				return numGovt;
			}

			public void setNumGovt(String numGovt) {
				this.numGovt = numGovt;
			}

			public String getField() {
				return field;
			}

			public void setField(String field) {
				this.field = field;
			}

			public String getCountry() {
				return country;
			}

			public void setCountry(String country) {
				this.country = country;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}

			public String getCounty() {
				return county;
			}

			public void setCounty(String county) {
				this.county = county;
			}

			public String getBlock() {
				return block;
			}

			public void setBlock(String block) {
				this.block = block;
			}

			public String getTimeZone() {
				return timeZone;
			}

			public void setTimeZone(String timeZone) {
				this.timeZone = timeZone;
			}

			public String getOperator() {
				return operator;
			}

			public void setOperator(String operator) {
				this.operator = operator;
			}

			public String getNumAPI() {
				if (Strings.isNullOrEmpty(numAPI)) {
					numAPI = "";
				}
				return numAPI;
			}

			public void setNumAPI(String numAPI) {
				this.numAPI = numAPI;
			}

			public String getdTimSpud() {
				return dTimSpud;
			}

			public void setdTimSpud(String dTimSpud) {
				this.dTimSpud = dTimSpud;
			}

			public List<WellDatum> getWellDatum() {
				return wellDatum;
			}

			public void setWellDatum(List<WellDatum> wellDatum) {
				this.wellDatum = wellDatum;
			}

			public GroundElevation getGroundElevation() {
				return groundElevation;
			}

			public void setGroundElevation(GroundElevation groundElevation) {
				this.groundElevation = groundElevation;
			}

			public WaterDepth getWaterDepth() {
				return waterDepth;
			}

			public void setWaterDepth(WaterDepth waterDepth) {
				this.waterDepth = waterDepth;
			}

			public List<WellLocation> getWellLocation() {
				return wellLocation;
			}

			public void setWellLocation(List<WellLocation> wellLocation) {
				this.wellLocation = wellLocation;
			}

			public CommonData getCommonData() {
				return commonData;
			}

			public void setCommonData(CommonData commonData) {
				this.commonData = commonData;
			}

			public String getUid() {
				return uid;
			}

			public void setUid(String uid) {
				this.uid = uid;
			}

			// inner classes
			public static class WaterDepth {
				private String uom;

				public String getUom() {
					return uom;
				}

				public void setUom(String uom) {
					this.uom = uom;
				}
			}

			public static class CommonData {
				private String dTimLastChange;
				private DefaultDatum defaultDatum;

				public String getdTimLastChange() {
					return dTimLastChange;
				}

				public void setdTimLastChange(String dTimLastChange) {
					this.dTimLastChange = dTimLastChange;
				}

				public DefaultDatum getDefaultDatum() {
					if (defaultDatum == null) {
						defaultDatum = new DefaultDatum();
					}
					return defaultDatum;
				}

				public void setDefaultDatum(DefaultDatum defaultDatum) {
					this.defaultDatum = defaultDatum;
				}

				// inner class
				public static class DefaultDatum {
					private String UidRef;
					private String Value;

					public String getUidRef() {
						return UidRef;
					}

					public void setUidRef(String uidRef) {
						UidRef = uidRef;
					}

					public String getValue() {
						return Value;
					}

					public void setValue(String value) {
						Value = value;
					}
				}
			}

			public static class GroundElevation {
				private String uom;
				private String datum;
				private int Value;

				public String getUom() {
					return uom;
				}

				public void setUom(String uom) {
					this.uom = uom;
				}

				public String getDatum() {
					return datum;
				}

				public void setDatum(String datum) {
					this.datum = datum;
				}

				public int getValue() {
					return Value;
				}

				public void setValue(int value) {
					Value = value;
				}
			}

			public static class WellDatum {
				private String name;
				private int code;
				private String uid;
				private Elevation elevation;

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}

				public int getCode() {
					return code;
				}

				public void setCode(int code) {
					this.code = code;
				}

				public String getUid() {
					return uid;
				}

				public void setUid(String uid) {
					this.uid = uid;
				}

				public Elevation getElevation() {
					if (elevation == null) {
						elevation = new Elevation();
					}
					return elevation;
				}

				public void setElevation(Elevation elevation) {
					this.elevation = elevation;
				}

				public static class Elevation {
					private String uom;
					private String datum;
					private int Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public String getDatum() {
						return datum;
					}

					public void setDatum(String datum) {
						this.datum = datum;
					}

					public int getValue() {
						return Value;
					}

					public void setValue(int value) {
						Value = value;
					}
				}
			}

			public static class WellLocation {
				private String uid;
				private Latitude latitude;
				private Longitude longitude;
				private Easting easting;
				private Northing northing;
				private LocalX localX;
				private LocalY localY;

				public String getUid() {
					return uid;
				}

				public void setUid(String uid) {
					this.uid = uid;
				}

				public Latitude getLatitude() {
					if (latitude == null) {
						latitude = new Latitude();
					}
					return latitude;
				}

				public void setLatitude(Latitude latitude) {
					this.latitude = latitude;
				}

				public Longitude getLongitude() {
					if (longitude == null) {
						longitude = new Longitude();
					}
					return longitude;
				}

				public void setLongitude(Longitude longitude) {
					this.longitude = longitude;
				}

				public Easting getEasting() {
					if (easting == null) {
						easting = new Easting();
					}
					return easting;
				}

				public void setEasting(Easting easting) {
					this.easting = easting;
				}

				public Northing getNorthing() {
					if (northing == null) {
						northing = new Northing();
					}
					return northing;
				}

				public void setNorthing(Northing northing) {
					this.northing = northing;
				}

				public LocalX getLocalX() {
					if (localX == null) {
						localX = new LocalX();
					}
					return localX;
				}

				public void setLocalX(LocalX localX) {
					this.localX = localX;
				}

				public LocalY getLocalY() {
					if (localY == null) {
						localY = new LocalY();
					}
					return localY;
				}

				public void setLocalY(LocalY localY) {
					this.localY = localY;
				}

				public static class LocalX {
					private String uom;
					private float Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public float getValue() {
						return Value;
					}

					public void setValue(float value) {
						Value = value;
					}
				}

				public static class LocalY {
					private String uom;
					private float Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public float getValue() {
						return Value;
					}

					public void setValue(float value) {
						Value = value;
					}
				}

				public static class Latitude {
					private String uom;
					private float Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public float getValue() {
						return Value;
					}

					public void setValue(float value) {
						Value = value;
					}
				}

				public static class Longitude {
					private String uom;
					private float Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public float getValue() {
						return Value;
					}

					public void setValue(float value) {
						Value = value;
					}
				}

				public static class Easting {
					private String uom;
					private float Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public float getValue() {
						return Value;
					}

					public void setValue(float value) {
						Value = value;
					}
				}

				public static class Northing {
					private String uom;
					private float Value;

					public String getUom() {
						return uom;
					}

					public void setUom(String uom) {
						this.uom = uom;
					}

					public float getValue() {
						return Value;
					}

					public void setValue(float value) {
						Value = value;
					}
				}
			}
		}
	}

	// Convenience methods
	public UUID getWellId() {
		return UUID.fromString(this.getRtsMessage().getBody().getWell().getUid());
	}
	public Date getTimLastChange() {
		return Utility.convertUtcToDate(this.getRtsMessage().getBody().getWell().getdTimSpud());
	}
}
