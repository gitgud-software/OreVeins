OreVeins
========
Authors: Kevin M. and Possibly Kevin S.

A naive attempt at creating realistic ore controls for a minecraft bukkit plugin
Mojang has in the past taken a simplistic approach to Ore Generation and distribution in Minecraft. To understand why
This plugin is in development, lets first look at how Minecraft 'nilla generates ores.

'Nilla World structure & Ore Generation
=======================================

In a Minecraft world, every block exists in an object called a 'chunk.' A chunk is simply a 16x16x256 slice of the world
in which all the blocks reside. Every tree, leaf, dirt, stone, and lava block has both a coordinate based on the (0,0,0)
vector of the chunk, and based on the coordinate of the chunk itself in relation to the (0,0) chunk coordinate. This
means that if you place a block at the coordinate x:33 y:64 z:34, you're placing in the (3,3) chunk, and at coordinates
(1,64,2) within that chunk.

Ores in minecraft then are distributed in two ways within these chunks: by depth and by 'mass'. Mojang and most of the
proggramming world feels that rarer ores should be deeper in the world (where deeper == harder or something?) and more 
common and necessary ores should be able to be found over a wide range of depths. As a result, ores like Diamond and 
Gold are only found below a certain depth, while coal and iron can be found quite high up. 

In addition to this, each ore 'vein' is given a simple geometric configuraiton of a little glob of blocks. Iron, for example
is commonly found as groups of 4-6 blocks, while Coal 'veins' can be found in as big masses as 30 blocks. As far as I
know this distinction is arbitrary and only based upon what the felt 'rarity' of these ores should be. 

It should be noted that only one type of ore, Emerald Ore, is found distributed by location (in this case, extreme hills
Biomes)

OverView of Real world Ore Controls
===================================

It would be naive to think that including a perfectly accurate model of ore geology and structural controls into minecraft
would be feasable, easy, or even desireable. Still, it is useful to have some background about the structures and
distribution of the minecraft ores. It is my hope that real world ore controls can be leveraged to create a more
engaging server environment; one where caving and exploration, both below and above the surface, pay larger dividends
than blindly creating drift mines.

Coal
====
 Coal is derived from heated and compressed organic matter. Often, it starts out as a vast swamp or flood plain, where organic matter is deposited in an Anoxic environment that prevents its decomposition. Topographically, this
happend on the edge of a continental shelf, or in big, broad, flat basins. The O-matter is then buried by sediment, 
compressed by the weight of rock above it, and slightly heated by the deep earth. This turns a layer of O-matter that may be hundreds of feet thick into one a few tens of feet think, though actual coal veins can vary from a few inches to
many yards wide. These veins have a planar structure, a sheet-like quality, and may extend for many miles in both of the
principle directions of the plane extension. Additionally, the Coal sheet may be folded by tectonic forces, or 'burnt' to graphite by local igneous (magma) intrusions.

For the purposes of this project, the desireable qualities we'd like to emulate in the coal veins are the:
-extensional nature of the coal bed/plane
-tectonic 'effects' on the plane
-regional restriction of the plane to areas that favor its existence

Iron
====
Iron is an extremely common element in nature, and is found in mineable grades in a wide range of geologic settings. Just a few will be listed here.

Bog Iron: occasionally in swamps and in waterlogged soils, Fe3+ & Fe2+ complexes will be precipitated out of solution by bacteria and into small nodules of iron.

Banded Iron Formations BIFs: BIFs are the major source of commercial iron. Between 4 & 3 Billion years ago, the Earth's oceans were so full of sulphur and iron minerals dissolved in solution that it was likely that the ocean appeared a deep green. However, as soon as photoautotrophs appeared on the scene and began pumping out O2, sulphide and iron minerals began to oxidize and fall to the sea floor. The compressed and often highly folded sediments are called Banded Iron formations, and are nearly pure hematite. They range from just a few inches thick to a few yards, and once were very common in old, sedimentary terranes. 

hydrothermal Iron Oxides: Hydrothermal deposits are a very common depositor of interesting metals. They form in many geologic settings and take on a wide variety of structures. They consist of a heat source, some control on the flow of hot water, and some temperature/pressure gradient, either emanating radially from the waterflow or in line with it. As a result, hydrothermal Iron Oxides are branched, linear features with cross sections that vary with their particular geologic setting. However hydrothermal iron is often not very rich.

For the purposes of this project, the desireable qualities we'd like to emulate in Iron deposits are:
-random, low grade Bog iron found in the dirt under swamps
-BIF planes and folded planes found every now and then, rare but extensive when they are
-hydrothermal Iron Oxides found in many of the other hydrothermal veins scattered about the map

Gold
====

Gold economic geology is actually quite a fascinating field, so any summary will be limiting by necessity. For this summary, we will limit ourselves to hardrock/primary native gold deposits.

Primary Native gold deposits are found in structures called Quartz Veins. These veins start initially as mineral laden, hot (100-600 C), high pressure (3-300 bar), water flowing through crevices, cracks, and weaknesses in rocks, often towards a surface hotspring. Often bedding planes, local fault zones, and brittle areas of the rock will guide these gold bearing solutions. Small earthquakes will cause large relative changes in pressure along these water paths which in turn precipitate native gold onto the rock wall. Large amounts of gold are often found in spotty, small veins, or lots of small grains will be found in quartz veins hundreds of feet wide.

Within Veins themsleves, multiple interesting structures exist. Often gold bearing veins can be completely barren of gold up to a certain point, contain rare bonanzas where large nuggets are formed, will branch, dissapear, be randomly offset by anywhere from a few inches to many hundreds of feet, and may be intertwined and interconnected with other vein systems.

The veins can take the form of a snaking tube, lenticular sheets, small en-echelon lenses, large thick planes, or highly folded and altered 'squiggle' veins.

For the purposes of this project, the desireable qualities we'd like to emulate in Gold veins are:
-A few, very large Veins interconnected with smaller 'stringer' veins
-Grades for each Vein that are allowed to vary with strike, contain 'bonanzas', and barren zones
-Grades that vary with vein structure, width,strike and extent
-Veins that exhibit a wide variety of structures, but are on the whole similar to other veins in the vicinity
-Overall vein trends and structures that emulate fault zones, breccia zones, and bedding planes.

Lapiz Lazuli
============
