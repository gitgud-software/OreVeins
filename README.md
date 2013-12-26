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

Coal 'Ore': Coal is derived from heated and compressed organic matter. Often, it starts out as a vast swamp or flood plain, 
where organic matter is deposited in an Anoxic environment that prevents its decomposition. 
