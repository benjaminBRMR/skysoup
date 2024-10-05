package eu.skysoup.skypvp.data.implementorings;

import lombok.Getter;

/**
 * Created: 23.01.2023 22:05
 *
 * @author thvf
 */
public enum SettingTypes {

    MSG(Permissions.DEFAULT.getPermission()),
    TPA(Permissions.DEFAULT.getPermission()),
    SPAWNTELEPORT(Permissions.DEFAULT.getPermission()),
    TITLES(Permissions.DEFAULT.getPermission()),


    // ADMIN

    CW(Permissions.CW.getPermission()),
    ADMINCW(Permissions.ADMIN.getPermission()),
    MSGSPY(Permissions.MSGSPY.getPermission());

    @Getter
    final String permission;

    SettingTypes(final String permission) {
        this.permission = permission;
    }
}
