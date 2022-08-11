
import React from 'react'
import Grid from '@material-ui/core/Grid'


const BikePath = ({path}) => {
  
  return <Grid container item spacing={2} xs={12}>
      <Grid item xs>
        {path['Departure station id']}
      </Grid>
      <Grid item xs>
        {path['Departure station name']}
      </Grid>
      <Grid item xs>
        {new Date(path['Departure']).toUTCString()}
      </Grid>
      <Grid item xs>
        {path['Return station id']}
      </Grid>
      <Grid item xs>
        {path['Return station name']}
      </Grid>
      <Grid item xs>
        {new Date(path['Return']).toUTCString()}
      </Grid>
      <Grid item xs>
        {path['Distance']}
      </Grid>
      <Grid item xs>
        {path['Duration']}
      </Grid>
    </Grid>
}

export default BikePath