# Cleaner-AZFDC

This is an application that will calculate the final damage done by a single shot or load of a weapon in the mobile game Azur Lane. Calculations are calculated through the stats given by the ship, weapons and equipment, and skills.
This calculates damage done to ships in world 1-13 (NOT PVP OR SIREN MODE).

# Important-Info
<B> Game changes/updates will be implemented when able. </B>
<B> Currently updated to: PR4 </B>

Calculations are done with preset conditions:
- Ships are level 120 and retroed (if it has a retro) and stats are represented of that. If the ship has a retrofit that changes the ship type, both the pre and post retro level 120 stats will be shown.
- Danger level is reduced to the max of the given world
- Weapons are at max stats, not max enhanced
- All skills are at level 10 stats
- Conditions for the skills are met for most, with some needing exceptions (such as the correct weapon, weapon type, or ship type)
- For planes and their bombs and torpedos, the planes will not drop x amount as listed, and will need the amount to be inputed
- Equipment that has not been fully updated yet will have 0 filled for all the stats

<B>WARNING: Take these calculations with a grain of salt. These are very close estimates (Hopefully) of the damage you can expect a ship to do under these conditions.</B> In regards to skills, you will have almost complete freedom to apply whatever skill and the stats that come with those skills. Do note that this will greatly skew the predicted damage.
<B>Do what you want, but if you want to get close-to-accurate data, only apply the skills that benefit said ship.</B>

Updates are based off of wiki data, so there is possibility of changed skill names.

Currently going through skills that have an extra effect along the lines of "Boost this ships stats by x...and also boost x for x ship or ship types" and having it so it only applys the latter instead of the whole skill applying to the ship. (Derp)

Let us know if there are any errors we may have missed

Thank You to those who helped with this:

knv96, wfh111, and Samu :).
