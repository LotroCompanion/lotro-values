This project provides:
- POJOs to represent stat/property values as found in LOTRO
- related facilities such as:
	- persistence as raw strings
	- loading from raw strings

Some stats are implemented by standard Java types, such as Integer, Long, Float, BitSet.
Some other stats are implemented through dedicated types: TBD

The facilities here are meant to be used by:
- lotro-dat-utils: to build stat values from value types found in lotro-dat-utils
- lotro-core: to read/write and use those values.
For instance, it is planned that BasicStatSets and StatProvider/StatsProvider shall use those.

This project was created to avoid a dependency between lotro-dat-utils and lotro-core. It is planned that both shall use this project.

As a first step, we will only handle values, and we will not deal with metadatas (see PropertyDefinition).

