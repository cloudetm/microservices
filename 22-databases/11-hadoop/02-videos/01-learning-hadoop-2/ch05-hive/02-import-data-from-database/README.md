# Importing Data Directly from a Database

*terminal*

- enter following in terminal

```
[cloudera@quickstart usr]$ sqoop import \
> --connect jdbc:mysql://127.0.0.1:3306/xyzcorp \
> --table Customers \
> --username root -P \
> --hive-import \
> --hive-table rsk.customers \
> --direct -m 1
```

- wait for it to be completed

*cloudera*

- `File Browser`, `/user/hive/warehouse/rsk.db/customers/part-m-00000`

```
1000245Toms Produce100 Toms LaneCharlotteNC28201
1000356Contruction Depot102 Peachtree RoadAtlantaGA30350
1000567Jupiter Networks203 Packet RoadSunnyvaleCA94085
1000890Scottish Petroleum304 Scottish BlvdHoustonTX77079
1000446Yummy Restaurants405 Cardinal AvenueLouisvilleKY40203
1000386Andicle506 Database DriveRedwood CityCA94061
1000336Banana Computer607 Infinity RoadCupertinoCA94024
1000998McDougals708 Burgerton AvenueOak BrookIL60523
1000326Jimsung809 Electronic LaneRidgefield ParkNJ07660
1000269Dash Cellular900 Dash DriveOverland ParkKS66204
```
