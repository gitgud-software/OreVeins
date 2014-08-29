OreVeins

Copyright (C) 2014  Kevin Mendoza
kevinmendoza@mac.com
Major Contributors: Kevin Song, Alex Lin, Darren Chang, Drew Parliament, Zeno Hao

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.



A naive attempt at creating realistic ore controls for a minecraft bukkit plugin. Mojang has in the past taken a simplistic approach to Ore Generation and distribution in Minecraft. To understand why This plugin is in development, lets first look at how Minecraft 'nilla generates ores.
'Nilla World structure & Ore Generation
Ores in minecraft then are distributed in two ways within these chunks: by depth and by 'mass'. Mojang and most of the programming world feels that rarer ores should be deeper in the world (where deeper == harder or something?) and more common and necessary ores should be able to be found over a wide range of depths. As a result, ores like Diamond and Gold are only found below a certain depth, while coal and iron can be found quite high up.
In addition to this, each ore 'vein' is given a simple geometric configuraiton of a little glob of blocks. Iron, for exampl, is commonly found as groups of 4-6 blocks, while Coal 'veins' can be found in as big masses as 30 blocks. As far as I know this distinction is arbitrary and only based upon what the felt 'rarity' of these ores should be.
It should be noted that only one type of ore, Emerald Ore, is found distributed by location (in this case, extreme hills Biomes)

OverView of Real world Ore Controls
It would be naive to think that including a perfectly accurate model of ore geology and structural controls into minecraft would be feasable, easy, or even desireable. Still, it is useful to have some background about the structures and distribution of the minecraft ores. It is my hope that real world ore controls can be leveraged to create a more engaging server environment; one where caving and exploration, both below and above the surface, pay larger dividends than blindly creating drift mines.

Coal
Coal is derived from heated and compressed organic matter. Often, it starts out as a vast swamp or flood plain, where organic matter is deposited in an Anoxic environment that prevents its decomposition. Topographically, this happend on the edge of a continental shelf, or in big, broad, flat basins. The O-matter is then buried by sediment, compressed by the weight of rock above it, and slightly heated by the deep earth. This turns a layer of O-matter that may be hundreds of feet thick into one a few tens of feet think, though actual coal veins can vary from a few inches to many yards wide. These veins have a planar structure, a sheet-like quality, and may extend for many miles in both of the principle directions of the plane extension. Additionally, the Coal sheet may be folded by tectonic forces, or 'burnt' to graphite by local igneous (magma) intrusions.
For the purposes of this project, the desireable qualities we'd like to emulate in the coal veins are the: -extensional nature of the coal bed/plane -tectonic 'effects' on the plane -regional restriction of the plane to areas that favor its existence

Iron
Iron is an extremely common element in nature, and is found in mineable grades in a wide range of geologic settings. Just a few will be listed here.
Bog Iron: occasionally in swamps and in waterlogged soils, Fe3+ & Fe2+ complexes will be precipitated out of solution by bacteria and into small nodules of iron. (currently Unimplemented)
Banded Iron Formations BIFs: BIFs are the major source of commercial iron. Between 4 & 3 Billion years ago, the Earth's oceans were so full of sulphur and iron minerals dissolved in solution that it was likely that the ocean appeared a deep green. However, as soon as photoautotrophs appeared on the scene and began pumping out O2, sulphide and iron minerals began to oxidize and fall to the sea floor. The compressed and often highly folded sediments are called Banded Iron formations, and are nearly pure hematite. They range from just a few inches thick to a few yards, and once were very common in old, sedimentary terranes.
hydrothermal Iron Oxides: Hydrothermal deposits are a very common depositor of interesting metals. They form in many geologic settings and take on a wide variety of structures. They consist of a heat source, some control on the flow of hot water, and some temperature/pressure gradient, either emanating radially from the waterflow or in line with it. As a result, hydrothermal Iron Oxides are branched, linear features with cross sections that vary with their particular geologic setting. However hydrothermal iron is often not very rich.
For the purposes of this project, the desireable qualities we'd like to emulate in Iron deposits are: -random, low grade Bog iron found in the dirt under swamps -BIF planes and folded planes found every now and then, rare but extensive when they are -hydrothermal Iron Oxides found in many of the other hydrothermal veins scattered about the map

Gold
Gold economic geology is actually quite a fascinating field, so any summary will be limiting by necessity. For this summary, we will limit ourselves to hardrock/primary native gold deposits.
Primary Native gold deposits are found in structures called Quartz Veins. These veins start initially as mineral laden, hot (100-600 C), high pressure (3-300 bar), water flowing through crevices, cracks, and weaknesses in rocks, often towards a surface hotspring. Often bedding planes, local fault zones, and brittle areas of the rock will guide these gold bearing solutions. Small earthquakes will cause large relative changes in pressure along these water paths which in turn precipitate native gold onto the rock wall. Large amounts of gold are often found in spotty, small veins, or lots of small grains will be found in quartz veins hundreds of feet wide.
Within Veins themsleves, multiple interesting structures exist. Often gold bearing veins can be completely barren of gold up to a certain point, contain rare bonanzas where large nuggets are formed, will branch, dissapear, be randomly offset by anywhere from a few inches to many hundreds of feet, and may be intertwined and interconnected with other vein systems.
The veins can take the form of a snaking tube, lenticular sheets, small en-echelon lenses, large thick planes, or highly folded and altered 'squiggle' veins.
For the purposes of this project, the desireable qualities we'd like to emulate in Gold veins are: -A few, very large Veins interconnected with smaller 'stringer' veins -Grades for each Vein that are allowed to vary with strike, contain 'bonanzas', and barren zones -Grades that vary with vein structure, width,strike and extent -Veins that exhibit a wide variety of structures, but are on the whole similar to other veins in the vicinity -Overall vein trends and structures that emulate fault zones, breccia zones, and bedding planes.

Lapiz Lazuli
Lapiz Lazuli is a tectosilicate mineral that has been used since ancient times in pigments, decorations, and various expensive and fine trimmings. On an elemental basis, it is composed of nothing really interesting or surprising; just sodium, calcium, aluminum, silicon, oxygen, sulphur and chlorine. Thus its rarity comes from the fact that it occasionally occurs when an ingeous intrusion comes into contact with marble. Other minerals like pyrite, quartz, mica, and calcite sometimes also form along with Lapiz.
Lapiz deposits thus are expected to follow the original bedding planes of the marble along the igneous contact. This may be in the form of parallel metamorphososed planes, thick-skinned ellipsoids, and other such shapes.
For this project, the qualities of Lapiz Deposits which we will try and emulate are the following: -majority of deposits in planar, tightly folded sheets -sheets may be discontinuous and broken up, with the majority of physical ore blocks being grouped in small groupings rather than large ones. -Individual sheets should be located approximately around eachother, such that lapiz bearing regions are formed

Emerald
Emerald is a beryllium, aluminum, silicon mineral colored by trace amounts of chromium and occasionally vanadium. As such it occurs in settings which favor the growth of large beryl minerals: high pressure, medium temperature, tectonically quiet settings. The Emeralds grow in small hydrothermal pockets where the gradient of temperature and pressure is generally low, and often occur with other gems and minerals. The pockets themselves range from a few inches in diameter to several tens of feet, and take the appearance of a geode. Individual pockets may be linked by veins or planes of large grained igneous rocks called pegmatites, and may bear emeralds as well.
Emeralds should occur as extensive networks of these pockets connected by fissures of emeralds. For this project, Emerald deposits will have the following featuers: -Pockets of emeralds will take the form of hollow ellipsoidal geodes, where the x,y, and z axes of the ellipsoid itself can ocurr in many orientations. -Pockets have the chance to overlap, be congruent to each other, and occur in sizes varying from a single ore block to a max of 20 blocks in diameter -pockets will be linked by short planar structures no more than 7-8 blocks in length, and may be folded or bent -these planar structuers will have varying probabilities of having the emerald block forming in it, and will exist as single block thickness structures

Redstone
The clear real-world analouge to redstone is copper. Both redstone and copper are used in their respective worlds for circuitry and can carry signals. Oxidized copper is in fact sometimes reddish. However real world copper deposits are much more common than gold, and very few examples of native copper deposits have survived to this day. Therefore redstone in this project will be assumed to take on a hydrothermal character (much like native copper veins do), but will be much smaller and less varied than the gold veins above.
So for this redstone ore impelmentation, the following procecure will be adhered to: -Small 'pockets' of restone no more than 3-4 blocks in diameter will be interlinked by a network of short planar veins (no more than 7-12 long and 2-3 wide) of varying grade. -Redstone should occur spacially between deposits of gold and deposits of lapiz, regardless of existing emerald deposits.

Diamond
Diamond in nature forms in the deep crust under high pressure-low temperature conditions. It is brought to the surface by supersonic erupions of magma, which leave ice-cream cone like crater at the surface which may exhibit a characteristic cinder-cone shape of ejecta. This particular volcanic rock, called kimberlite, sometimes forms long planar structures connected to the main pipe underground, usually no more than a few tens of meters wide, but extending many hundreds of feet.
For this implementation, Diamond ores will have the following qualities: -Diamond deposits will be extraordinarily rare (we'll need to play around with the frequency of these structures) -they'll consist of either progressed volcanic diatreme structures forming broad cones which flatten at the top and narrow at the bottom, and having a small chance of diamond occurring within them. -both failed (small fingers that don't/didn't reach the surface) and progressed deposits will have a chance of planar structures extending from deep underground, no more than 50 blocks x 40 blocks x 7 blocks, and having a lower chance of diamonds in them than the main finger/diatreme branch.